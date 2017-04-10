package hu.unideb.smartcampus.service.api.impl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import hu.unideb.smartcampus.service.api.CalendarEventType;
import hu.unideb.smartcampus.service.api.CalendarService;
import hu.unideb.smartcampus.service.api.UnparsableCalendarEventSummaryException;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.AppointmentTime;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectType;
import hu.unideb.smartcampus.service.api.calendar.parser.CalendarAppointmentDateTimeParser;
import hu.unideb.smartcampus.service.api.calendar.parser.CalendarSubjectDetailsParser;
import hu.unideb.smartcampus.service.api.domain.InstructorWrapper;
import hu.unideb.smartcampus.shared.exception.InputParseException;
import hu.unideb.smartcampus.webservice.api.factory.HttpRequestType;
import hu.unideb.smartcampus.webservice.api.neptun.StudentCourse;
import hu.unideb.smartcampus.webservice.api.neptun.StudentTimeTable;
import hu.unideb.smartcampus.webservice.api.provider.HttpResponseProvider;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.component.VEvent;

@Service
public class CalendarServiceImpl implements CalendarService {

  private static final String UNKNOWN = "Ismeretlen";

  private static final String SUBJECT_TYPE_PATTERN = "[A-Z]{4}[0-9]{3}[E|L|G](-[A-Z|0-9]{2})?";

  private static final String FIRST_SEMESTER = "1";

  private static final String SEMESTER_SPLIT = "/";

  private static final int SEMESTER_START_INDEX = 0;

  private static final int SEMESTER_INDEX = 2;

  private static final Logger LOGGER = LoggerFactory.getLogger(CalendarServiceImpl.class);

  @Autowired
  private HttpResponseProvider httpResponseProvider;

  @Autowired
  private CalendarAppointmentDateTimeParser calendarAppointmentDateTimeParser;

  @Autowired
  private CalendarSubjectDetailsParser calendarSubjectDetailsParser;

  @Override
  public List<SubjectEvent> downloadCalendar(String urlToParse) throws InputParseException {
    final LinkedList<SubjectEvent> subjectEvents = Lists.newLinkedList();
    try (final InputStream is = httpResponseProvider
        .sendHttpRequest(urlToParse, HttpRequestType.HTTP_REQUEST_GET).getEntity().getContent()) {
      for (Object prop : new CalendarBuilder().build(is).getComponents()) {
        final VEvent event = (VEvent) prop;
        try {
          if (CalendarEventType
              .matches(event.getSummary().getValue()) == CalendarEventType.TIMETABLE) {
            final SubjectDetails subjectDetails =
                this.calendarSubjectDetailsParser.parseSubjectDetails(event);
            final AppointmentTime appointmentTime =
                this.calendarAppointmentDateTimeParser.parseAppointmentDateTime(event);

            subjectBuilderListPopulator(subjectEvents, SubjectEvent.builder()
                .subjectDetails(subjectDetails)
                .roomLocation(event.getLocation().getValue())
                .appointmentTimeList(Lists.newArrayList())
                .build(),
                appointmentTime);
          }
        } catch (UnparsableCalendarEventSummaryException e) {
          throw new InputParseException(e);
        }
      }
    } catch (final IOException | ParserException e) {
      throw new InputParseException(e);
    }
    LOGGER.info("============================================================\n\n\n");
    LOGGER.info("value: {}", subjectEvents);
    return subjectEvents;
  }

  private void subjectBuilderListPopulator(final LinkedList<SubjectEvent> subjectEvents,
      final SubjectEvent subjectEvent, final AppointmentTime appointmentTime) {
    final Optional<SubjectEvent> subjectEventOptional = subjectEvents.parallelStream()
        .filter(actualSubjectEvent -> actualSubjectEvent.equals(subjectEvent))
        .findFirst();

    if (subjectEventOptional.isPresent()) {
      subjectEventOptional.get().getAppointmentTimeList().add(appointmentTime);
    } else {
      subjectEvent.getAppointmentTimeList().add(appointmentTime);
      subjectEvents.add(subjectEvent);
    }
  }

  private void subjectBuilderListPopulator(final List<SubjectEvent> subjectEvents,
      final SubjectEvent subjectEvent, final List<AppointmentTime> appointmentTime) {
    final Optional<SubjectEvent> subjectEventOptional = subjectEvents.parallelStream()
        .filter(actualSubjectEvent -> actualSubjectEvent.equals(subjectEvent))
        .findFirst();

    if (subjectEventOptional.isPresent()) {
      subjectEventOptional.get().getAppointmentTimeList().addAll(appointmentTime);
    } else {
      subjectEvent.getAppointmentTimeList().addAll(appointmentTime);
      subjectEvents.add(subjectEvent);
    }
  }

  @Override
  public List<SubjectEvent> downloadStudentTimeTable(StudentTimeTable studentTimeTable) {
    List<SubjectEvent> events = new ArrayList<>();
    List<StudentCourse> courses = studentTimeTable.getCourses();
    Map<String, List<StudentCourse>> bySemester =
        courses.stream().collect(Collectors.groupingBy(StudentCourse::getSemester));
    List<StudentCourse> lastSemesterCourses = new ArrayList<>(
        getFilteredCourseList(bySemester.get(findLastSemesterKey(bySemester.keySet()))));
    for (StudentCourse studentCourse : lastSemesterCourses) {
      SubjectDetails subjectDetails = createSubjectDetails(courses, studentCourse);
      subjectBuilderListPopulator(events,
          SubjectEvent.builder()
              .subjectDetails(subjectDetails)
              .roomLocation(getRoomLocation(studentCourse))
              .appointmentTimeList(Lists.newArrayList())
              .build(),
          getAppointmentListByCourseCode(courses, studentCourse.getCourseCode()));
    }
    return events;
  }

  private SubjectDetails createSubjectDetails(List<StudentCourse> courses,
      StudentCourse studentCourse) {
    return SubjectDetails.builder()
        .subjectType(createSubjectType(studentCourse.getCourseCode()))
        .courseCode(studentCourse.getCourseCode())
        .subjectName(studentCourse.getSubjectName())
        .endPeriod(getEndPeriod(studentCourse.getSemester()))
        .startPeriod(getStartPeriod(studentCourse.getSemester()))
        .instructors(getTeachersByCourseCode(courses, studentCourse))
        .build();
  }

  private String findLastSemesterKey(Set<String> keys) {
    return keys.stream().sorted(Comparator.reverseOrder()).findFirst().get();
  }

  private String getRoomLocation(StudentCourse studentCourse) {
    String classroomCode = studentCourse.getClassroomCode();
    return classroomCode != null ? classroomCode : UNKNOWN;
  }


  private List<InstructorWrapper> getTeachersByCourseCode(List<StudentCourse> courses,
      StudentCourse course) {
    List<InstructorWrapper> result = new ArrayList<>();
    if (course.getCourseCode() == null)
      return new ArrayList<>();
    List<StudentCourse> collect = courses.stream()
        .filter(p -> course.getSemester().equals(p.getSemester()))
        .filter(p -> course.getCourseCode().equals(p.getCourseCode()))
        .collect(Collectors.toList());
    for (StudentCourse studentCourse : collect) {
      result.add(InstructorWrapper.builder()
          .name(studentCourse.getLecturerDisplayName())
          .neptunIdentifier(studentCourse.getLecturerNeptunIdentifier())
          .build());
    }
    return result.stream().distinct().collect(Collectors.toList());
  }

  protected SubjectType createSubjectType(String courseCode) {
    final char subjectTypeCode = readSubjectType(courseCode);
    return mapSubjectTypeCodeToSubjectType(subjectTypeCode);
  }

  private char readSubjectType(String courseCode) {
    return courseCode.matches(SUBJECT_TYPE_PATTERN) ? courseCode.charAt(7) : 'O';
  }

  protected SubjectType mapSubjectTypeCodeToSubjectType(final char subjectTypeCode) {
    return Lists.newArrayList(SubjectType.values())
        .stream()
        .filter(s -> s.getSubjectTypeCode() == subjectTypeCode)
        .findFirst()
        .orElse(SubjectType.OTHER);
  }

  private List<AppointmentTime> getAppointmentListByCourseCode(List<StudentCourse> courses,
      String courseCode) {
    List<AppointmentTime> result = new ArrayList<>();
    List<StudentCourse> filteredCourseList = filterCoursesByCourseCode(courses, courseCode);
    for (StudentCourse studentCourse : filteredCourseList) {
      result.add(AppointmentTime.builder()
          .startDateTime(parseDate(studentCourse.getStartTime()))
          .endDateTime(parseDate(studentCourse.getEndTime()))
          .build());
    }
    return result;
  }

  private LocalDateTime parseDate(String date) {
    if (date == null)
      return null;
    return LocalDateTime.parse(date.split("\\+")[0]);
  }

  private List<StudentCourse> filterCoursesByCourseCode(List<StudentCourse> courses,
      String courseCode) {
    return courses.stream()
        .filter(p -> p.getCourseCode().equals(courseCode)).collect(Collectors.toList());
  }

  private List<StudentCourse> getFilteredCourseList(List<StudentCourse> courses) {
    return courses.stream()
        .filter(distinctByKey(course -> course.getCourseCode())).collect(Collectors.toList());
  }

  private LocalDate getEndPeriod(String semester) {
    LocalDate result;
    String[] splittedSemester = semester.split(SEMESTER_SPLIT);
    String semesterNumber = splittedSemester[SEMESTER_INDEX];
    String year = splittedSemester[SEMESTER_START_INDEX];
    if (isFirstSemester(semesterNumber)) {
      result = LocalDate.of(Integer.valueOf(year), Month.DECEMBER, 31);
    } else {
      result = LocalDate.of(Integer.valueOf(year) + 1, Month.MAY, 31);
    }
    return result;
  }

  private LocalDate getStartPeriod(String semester) {
    LocalDate result;
    String[] splittedSemester = semester.split(SEMESTER_SPLIT);
    String semesterNumber = splittedSemester[SEMESTER_INDEX];
    String year = splittedSemester[SEMESTER_START_INDEX];
    if (isFirstSemester(semesterNumber)) {
      result = LocalDate.of(Integer.valueOf(year), Month.SEPTEMBER, 1);
    } else {
      result = LocalDate.of(Integer.valueOf(year) + 1, Month.FEBRUARY, 1);
    }
    return result;
  }

  private boolean isFirstSemester(String semesterNumber) {
    return FIRST_SEMESTER.equals(semesterNumber);
  }

  private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }

}

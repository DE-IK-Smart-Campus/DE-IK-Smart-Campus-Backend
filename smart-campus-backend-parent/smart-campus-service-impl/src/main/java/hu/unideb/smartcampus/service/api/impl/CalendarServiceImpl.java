package hu.unideb.smartcampus.service.api.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import hu.unideb.smartcampus.persistence.repository.SubjectEventRepository;
import hu.unideb.smartcampus.service.api.CalendarEventType;
import hu.unideb.smartcampus.service.api.CalendarService;
import hu.unideb.smartcampus.service.api.UnparsableCalendarEventSummaryException;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.AppointmentTime;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.service.api.calendar.parser.CalendarAppointmentDateTimeParser;
import hu.unideb.smartcampus.service.api.calendar.parser.CalendarSubjectDetailsParser;
import hu.unideb.smartcampus.service.api.domain.CourseAppointment;
import hu.unideb.smartcampus.service.api.domain.User;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.CourseInfoWrapper;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.StudentTimeTableInfo;
import hu.unideb.smartcampus.service.api.util.CourseAppointmentUtil;
import hu.unideb.smartcampus.service.api.util.SemesterUtil;
import hu.unideb.smartcampus.service.api.util.SubjectTypeUtil;
import hu.unideb.smartcampus.service.api.util.TeacherUtil;
import hu.unideb.smartcampus.shared.exception.InputParseException;
import hu.unideb.smartcampus.webservice.api.factory.HttpRequestType;
import hu.unideb.smartcampus.webservice.api.neptun.StudentCourse;
import hu.unideb.smartcampus.webservice.api.provider.HttpResponseProvider;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.component.VEvent;

@Service
public class CalendarServiceImpl implements CalendarService {

  private static final String UNKNOWN = "Ismeretlen";

  private static final Logger LOGGER = LoggerFactory.getLogger(CalendarServiceImpl.class);

  @Autowired
  private HttpResponseProvider httpResponseProvider;

  @Autowired
  private CalendarAppointmentDateTimeParser calendarAppointmentDateTimeParser;

  @Autowired
  private CalendarSubjectDetailsParser calendarSubjectDetailsParser;

  @Autowired
  private SubjectEventRepository subjectEventRepository;

  @Autowired
  private ConversionService converter;

  @Autowired
  private SemesterUtil semesterUtil;

  @Autowired
  private TeacherUtil teacherUtil;

  @Autowired
  private SubjectTypeUtil subjectTypeUtil;

  @Autowired
  private CourseAppointmentUtil courseAppointmentUtil;

  @Override
  @Deprecated
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

  @Override
  public StudentTimeTableInfo downloadStudentTimeTable(CourseInfoWrapper courseInfoWrapper) {
    List<CourseAppointment> courseAppointments = new ArrayList<>();
    List<SubjectEvent> events = new ArrayList<>();
    List<SubjectDetails> subjectDetailsList = new ArrayList<>();

    for (StudentCourse studentCourse : courseInfoWrapper.getFilteredLastSemesterCourses()) {

      SubjectDetails subjectDetails =
          createSubjectDetails(courseInfoWrapper.getCourses(), studentCourse);

      subjectDetailsList.add(subjectDetails);

      SubjectEvent subjectEvent = SubjectEvent.builder()
          .subjectDetails(subjectDetails)
          .roomLocation(getRoomLocation(studentCourse))
          .courseCode(studentCourse.getCourseCode())
          .appointmentTimeList(Lists.newArrayList())
          .build();
      events.add(subjectEvent);
      courseAppointments.addAll(
          courseAppointmentUtil.getAppointmentListByCourseCode(
              courseInfoWrapper.getLastSemesterCourses(),
              studentCourse.getCourseCode(), subjectEvent));
    }

    return StudentTimeTableInfo.builder()
        .subjectEvents(events)
        .subjectDetails(subjectDetailsList)
        .build();
  }

  @Override
  public void pairEventWithAppointementByStudent(CourseInfoWrapper courseInfoWrapper, User user) {
    List<CourseAppointment> courseAppointmentList = user.getCourseAppointmentList();

    List<SubjectEvent> subjectEvents = user.getSubjectEventList();
    for (SubjectEvent event : subjectEvents) {
      if (event != null) {
        courseAppointmentList
            .addAll(
                getCourseAppointementsByInfoAndCourseAndEvent(courseInfoWrapper,
                    event));
      }
    }
  }

  private List<CourseAppointment> getCourseAppointementsByInfoAndCourseAndEvent(
      CourseInfoWrapper courseInfoWrapper, SubjectEvent subjectEvent) {

    return courseAppointmentUtil.getAppointmentListByCourseCode(
        courseInfoWrapper.getLastSemesterCourses(),
        subjectEvent.getCourseCode(),
        subjectEvent);
  }

  private SubjectDetails createSubjectDetails(List<StudentCourse> courses,
      StudentCourse studentCourse) {
    return SubjectDetails.builder()
        .semester(studentCourse.getSemester())
        .subjectName(studentCourse.getSubjectName())
        .courseCode(studentCourse.getCourseCode())
        .subjectType(subjectTypeUtil.createSubjectType(studentCourse.getCourseCode()))
        .endPeriod(semesterUtil.getEndPeriod(studentCourse.getSemester()))
        .startPeriod(semesterUtil.getStartPeriod(studentCourse.getSemester()))
        .instructors(teacherUtil.getTeachersByCourseCode(courses, studentCourse))
        .build();
  }

  private String getRoomLocation(StudentCourse studentCourse) {
    String classroomCode = studentCourse.getClassroomCode();
    return classroomCode != null ? classroomCode : UNKNOWN;
  }


  private void subjectBuilderListPopulator(final LinkedList<SubjectEvent> subjectEvents,
      final SubjectEvent subjectEvent, final AppointmentTime appointmentTime) {
    final Optional<SubjectEvent> subjectEventOptional = subjectEvents.stream()
        .filter(actualSubjectEvent -> actualSubjectEvent.equals(subjectEvent))
        .findFirst();

    if (subjectEventOptional.isPresent()) {
      subjectEventOptional.get().getAppointmentTimeList().add(appointmentTime);
    } else {
      subjectEvent.getAppointmentTimeList().add(appointmentTime);
      subjectEvents.add(subjectEvent);
    }
  }

}

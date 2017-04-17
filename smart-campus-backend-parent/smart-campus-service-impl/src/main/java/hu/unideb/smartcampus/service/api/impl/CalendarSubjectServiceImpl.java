package hu.unideb.smartcampus.service.api.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.service.api.CalendarSubjectService;
import hu.unideb.smartcampus.service.api.SubjectEventService;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.service.api.domain.CourseAppointment;
import hu.unideb.smartcampus.service.api.util.SemesterUtil;
import hu.unideb.smartcampus.shared.iq.request.CalendarSubjectsIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListUserAttendanceIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.AppointmentTimeIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;

/**
 * Calendar subject service implementation.
 */
@Service
public class CalendarSubjectServiceImpl implements CalendarSubjectService {

  private static final ZoneOffset ZERO_OFFSET = ZoneOffset.ofHours(0);

  private static final ZoneOffset HUNGARIAN_OFFSET = ZoneOffset.ofHours(2);

  @Autowired
  private SubjectEventService subjectEventService;

  @Autowired
  private SemesterUtil semesterUtil;

  @Transactional(readOnly = true)
  @Override
  public List<CalendarSubjectIqElement> getSubjectEvents(CalendarSubjectsIqRequest iq) {
    List<SubjectEvent> allSubjectEventByUsername =
        subjectEventService.getAllSubjectEventByUsername(iq.getStudent());
    return convertToIqElements(allSubjectEventByUsername, iq.getStudent());
  }

  @Transactional(readOnly = true)
  @Override
  public List<CalendarSubjectIqElement> getSubjectEventsWithinPeriod(CalendarSubjectsIqRequest iq) {
    String student = iq.getStudent();
    List<SubjectEvent> subjectsWithinRange =
        getSubjectsInRange(iq.getStartPeriod(), iq.getEndPeriod(), student);
    return convertToIqElements(subjectsWithinRange, student);
  }

  @Transactional(readOnly = true)
  @Override
  public List<CalendarSubjectIqElement> getSubjectEventsWithinPeriod(
      ListUserAttendanceIqRequest iq) {
    String student = iq.getStudent();
    List<SubjectEvent> subjectsWithinRange =
        getSubjectsInRange(semesterUtil.getStartPeriodInLong(), semesterUtil.getEndPeriodInLong(),
            student);
    return convertToIqElements(subjectsWithinRange, student);
  }

  private String appendColonOrWhiteSpace(List<String> teacherNames, Integer counter) {
    return counter.equals(teacherNames.size()) ? "" : ", ";
  }

  private void build(StringBuilder builder, List<String> teacherNames) {
    Integer counter = 0;
    for (String teacher : teacherNames) {
      counter++;
      builder.append(teacher).append(appendColonOrWhiteSpace(teacherNames, counter));
    }
  }

  private CalendarSubjectIqElement buildIqElement(SubjectEvent subjectEvent,
      List<CourseAppointment> list) {
    return CalendarSubjectIqElement.builder()
        .subjectName(subjectEvent.getSubjectDetails().getSubjectName())
        .where(subjectEvent.getRoomLocation())
        .appointmentTimes(convertToIqElement(list))
        .description(createDescriptionByTeachers(subjectEvent))
        .build();
  }

  private AppointmentTimeIqElement convertToIqElement(CourseAppointment appointmentTime) {
    return AppointmentTimeIqElement.builder()
        .id(appointmentTime.getId())
        .present(appointmentTime.getWasPresent())
        .from(appointmentTime.getStartDate().toEpochSecond(HUNGARIAN_OFFSET))
        .to(appointmentTime.getEndDate().toEpochSecond(HUNGARIAN_OFFSET))
        .when(appointmentTime
            .getStartDate()
            .toLocalDate()
            .atStartOfDay()
            .toEpochSecond(ZERO_OFFSET))
        .build();
  }

  private List<AppointmentTimeIqElement> convertToIqElement(
      List<CourseAppointment> appointmentTimeList) {
    return appointmentTimeList.stream()
        .map(this::convertToIqElement)
        .collect(Collectors.toList());
  }

  private List<CalendarSubjectIqElement> convertToIqElements(
      List<SubjectEvent> allSubjectEventByUsername, String username) {
    List<CalendarSubjectIqElement> calendarEvents = new ArrayList<>();
    for (SubjectEvent subjectEvent : allSubjectEventByUsername) {
      calendarEvents.add(buildIqElement(subjectEvent, subjectEventService
          .getCourseAppointmentByUsernameAndSubjectEvent(username, subjectEvent)));
    }
    return calendarEvents;
  }

  private String createDescriptionByTeachers(SubjectEvent subjectEvent) {
    StringBuilder builder = new StringBuilder();
    // List<String> teacherNames = subjectEvent.getSubjectDetails().getTeacherNames();
    // build(builder, teacherNames);
    return builder.toString();
  }

  private LocalDate getInLocalDate(Long period) {
    return Instant.ofEpochMilli(period * 1000).atZone(HUNGARIAN_OFFSET).toLocalDate();
  }

  private List<SubjectEvent> getSubjectsInRange(Long startPeriodLong, Long endPeriodLong,
      String student) {
    LocalDate startPeriod = getInLocalDate(startPeriodLong);
    LocalDate endPeriod = getInLocalDate(endPeriodLong);
    return subjectEventService.getSubjectEventWithinRangeByUsername(student,
        startPeriod,
        endPeriod);
  }

}

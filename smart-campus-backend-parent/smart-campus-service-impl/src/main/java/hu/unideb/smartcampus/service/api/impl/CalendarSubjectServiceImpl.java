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
import hu.unideb.smartcampus.shared.iq.request.CalendarSubjectsIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.AppointmentTimeIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;

/**
 * Calendar subject service implementation.
 */
@Service
public class CalendarSubjectServiceImpl implements CalendarSubjectService {

  private static final ZoneOffset HUNGARIAN_OFFSET = ZoneOffset.ofHours(1);

  @Autowired
  private SubjectEventService subjectEventService;

  @Transactional(readOnly = true)
  @Override
  public List<CalendarSubjectIqElement> getSubjectEvents(CalendarSubjectsIqRequest iq) {
    List<SubjectEvent> allSubjectEventByUsername =
        subjectEventService.getAllSubjectEventByUsername(iq.getStudent());
    List<CalendarSubjectIqElement> subjectEvents =
        convertToIqElements(allSubjectEventByUsername, iq.getStudent());
    return subjectEvents;
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

  private CalendarSubjectIqElement buildIqElement(SubjectEvent subjectEvent,
      List<CourseAppointment> list) {
    return CalendarSubjectIqElement.builder()
        .subjectName(subjectEvent.getSubjectDetails().getSubjectName())
        .where(subjectEvent.getRoomLocation())
        .appointmentTimes(convertToIqElement(list))
        .description(createDescriptionByTeachers(subjectEvent)).build();
  }

  private String createDescriptionByTeachers(SubjectEvent subjectEvent) {
    StringBuilder builder = new StringBuilder();
    // List<String> teacherNames = subjectEvent.getSubjectDetails().getTeacherNames();
    // build(builder, teacherNames);
    return builder.toString();
  }

  private void build(StringBuilder builder, List<String> teacherNames) {
    Integer counter = 0;
    for (String teacher : teacherNames) {
      counter++;
      builder.append(teacher).append(appendColonOrWhiteSpace(teacherNames, counter));
    }
  }

  private String appendColonOrWhiteSpace(List<String> teacherNames, Integer counter) {
    return counter.equals(teacherNames.size()) ? "" : ", ";
  }

  private List<AppointmentTimeIqElement> convertToIqElement(
      List<CourseAppointment> appointmentTimeList) {
    return appointmentTimeList.stream().map(this::convertToIqElement).collect(Collectors.toList());
  }

  private AppointmentTimeIqElement convertToIqElement(CourseAppointment appointmentTime) {
    return AppointmentTimeIqElement.builder()
        .from(appointmentTime.getStartDate().toEpochSecond(HUNGARIAN_OFFSET))
        .to(appointmentTime.getEndDate().toEpochSecond(HUNGARIAN_OFFSET))
        .when(appointmentTime
            .getStartDate().toLocalDate().atStartOfDay().toEpochSecond(HUNGARIAN_OFFSET))
        .build();
  }

  @Transactional(readOnly = true)
  @Override
  public List<CalendarSubjectIqElement> getSubjectEventsWithinPeriod(CalendarSubjectsIqRequest iq) {
    LocalDate startPeriod = getInLocalDate(iq.getStartPeriod());
    LocalDate endPeriod = getInLocalDate(iq.getEndPeriod());
    List<SubjectEvent> subjectsWithinRange =
        subjectEventService.getSubjectEventWithinRangeByUsername(iq.getStudent(), startPeriod,
            endPeriod);
    return convertToIqElements(subjectsWithinRange, iq.getStudent());
  }

  private LocalDate getInLocalDate(Long period) {
    return Instant.ofEpochMilli(period * 1000).atZone(HUNGARIAN_OFFSET).toLocalDate();
  }

}

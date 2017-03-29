package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.APPOINTMENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.APPOINTMENTS;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.DESCRIPTION;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.END_PERIOD;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.FROM;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.START_PERIOD;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.STUDENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.SUBJECT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.SUBJECT_EVENTS;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.SUBJECT_NAME;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.TO;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.WHEN;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.WHERE;

import java.util.ArrayList;
import java.util.List;

import hu.unideb.smartcampus.shared.iq.request.element.AppointmentTimeIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Calendar subject IQ request.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CalendarSubjectsIqRequest extends BaseSmartCampusIqRequest {

  /**
   * Element.
   */
  public static final String ELEMENT = "calendarSubjects";

  /**
   * Student's username.
   */
  private String student;

  /**
   * Start period.
   */
  private Long startPeriod;

  /**
   * End period.
   */
  private Long endPeriod;

  /**
   * Student's subjects.
   */
  private List<CalendarSubjectIqElement> subjectEvents;

  /**
   * Default constructor.
   */
  public CalendarSubjectsIqRequest() {
    super(ELEMENT);
    subjectEvents = new ArrayList<>();
  }

  /**
   * Constructs.
   */
  @Builder
  public CalendarSubjectsIqRequest(String student, List<CalendarSubjectIqElement> subjectEvents, Long startPeriod, Long endPeriod) {
    super(ELEMENT);
    this.student = student;
    this.subjectEvents = subjectEvents;
    this.startPeriod = startPeriod;
    this.endPeriod = endPeriod;
  }

  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    builder.append(tag(STUDENT, student));
    builder.append(tag(START_PERIOD, startPeriod));
    builder.append(tag(END_PERIOD, endPeriod));
    buildSubjects(builder);
  }

  private void buildSubjects(StringBuilder builder) {
    if (subjectEvents != null && !subjectEvents.isEmpty()) {
      builder.append(openTag(SUBJECT_EVENTS));
      for (CalendarSubjectIqElement calendarIqElement : subjectEvents) {
        buildSubject(builder, calendarIqElement);
      }
      builder.append(closeTag(SUBJECT_EVENTS));
    }
  }

  private void buildSubject(StringBuilder builder, CalendarSubjectIqElement calendarIqElement) {
    builder.append(openTag(SUBJECT));
    builder.append(tag(SUBJECT_NAME, calendarIqElement.getSubjectName()));
    builder.append(tag(WHERE, calendarIqElement.getWhere()));
    builder.append(tag(DESCRIPTION, calendarIqElement.getDescription()));
    buildAppointmens(builder, calendarIqElement);
    builder.append(closeTag(SUBJECT));
  }

  private void buildAppointmens(StringBuilder builder, CalendarSubjectIqElement calendarIqElement) {
    builder.append(openTag(APPOINTMENTS));
    buildAppointmentTimes(builder, calendarIqElement);
    builder.append(closeTag(APPOINTMENTS));
  }

  private void buildAppointmentTimes(StringBuilder builder,
      CalendarSubjectIqElement calendarIqElement) {
    for (AppointmentTimeIqElement appointment : calendarIqElement.getAppointmentTimes()) {
      builder.append(openTag(APPOINTMENT));
      builder.append(tag(WHEN, appointment.getWhen()));
      builder.append(tag(FROM, appointment.getFrom()));
      builder.append(tag(TO, appointment.getTo()));
      builder.append(closeTag(APPOINTMENT));
    }
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }
}

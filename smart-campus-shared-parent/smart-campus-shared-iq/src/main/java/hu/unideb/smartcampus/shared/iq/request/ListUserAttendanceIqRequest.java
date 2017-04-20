package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.APPOINTMENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.APPOINTMENTS;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.DESCRIPTION;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.FROM;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.ID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.PRESENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.STUDENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.SUBJECT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.SUBJECT_EVENTS;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.SUBJECT_ID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.SUBJECT_NAME;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.TO;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.WHEN;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.WHERE;

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
public class ListUserAttendanceIqRequest extends BaseSmartCampusIqRequest {

  /**
   * Element.
   */
  public static final String ELEMENT = "listUserAttendance";

  /**
   * Student's username.
   */
  private String student;

  /**
   * Student's subjects.
   */
  private List<CalendarSubjectIqElement> subjectEvents;

  /**
   * Default constructor.
   */
  public ListUserAttendanceIqRequest() {
    super(ELEMENT);
    subjectEvents = new ArrayList<>();
  }

  /**
   * Constructs.
   * 
   * @param student student's uid.
   * @param subjectEvents subject events.
   */
  @Builder
  public ListUserAttendanceIqRequest(String student, List<CalendarSubjectIqElement> subjectEvents) {
    super(ELEMENT);
    this.student = student;
    this.subjectEvents = subjectEvents;
  }

  @Override
  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    builder.append(tag(STUDENT, student));
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
    builder.append(tag(SUBJECT_ID, calendarIqElement.getId()));
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
      builder.append(tag(ID, appointment.getId()));
      builder.append(tag(WHEN, appointment.getWhen()));
      builder.append(tag(FROM, appointment.getFrom()));
      builder.append(tag(TO, appointment.getTo()));
      builder.append(tag(PRESENT, appointment.isPresent()));
      builder.append(closeTag(APPOINTMENT));
    }
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }
}

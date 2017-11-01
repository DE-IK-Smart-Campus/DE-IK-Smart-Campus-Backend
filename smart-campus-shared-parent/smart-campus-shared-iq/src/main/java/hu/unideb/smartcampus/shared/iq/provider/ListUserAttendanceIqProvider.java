package hu.unideb.smartcampus.shared.iq.provider;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.APPOINTMENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.APPOINTMENTS;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.DESCRIPTION;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.FROM;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.ID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.PRESENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.STUDENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.SUBJECT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.SUBJECT_ID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.SUBJECT_NAME;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.TO;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.WHEN;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListUserAttendanceFields.WHERE;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListUserAttendanceIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.AppointmentTimeIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;

/**
 * Attendance IQ provider.
 */
@SuppressWarnings({"PMD"})
public class ListUserAttendanceIqProvider
    extends BaseSmartCampusIqProvider<ListUserAttendanceIqRequest> {

  @Override
  public ListUserAttendanceIqRequest parse(XmlPullParser parser, int initialDepth)
      throws Exception {
    List<CalendarSubjectIqElement> subjectEvents = new ArrayList<>();
    List<AppointmentTimeIqElement> appointmentTimes = new ArrayList<>();
    String student = "";
    boolean done = false;
    CalendarSubjectIqElement subject = new CalendarSubjectIqElement();
    AppointmentTimeIqElement appointmentTime = new AppointmentTimeIqElement();
    int eventType = parser.getEventType();
    String text = "";
    while (!done) {
      eventType = parser.next();
      String tagname = parser.getName();
      switch (eventType) {
        case XmlPullParser.START_TAG:
          if (tagname.equalsIgnoreCase(SUBJECT)) {
            subject = new CalendarSubjectIqElement();
          } else if (tagname.equalsIgnoreCase(APPOINTMENT)) {
            appointmentTime = new AppointmentTimeIqElement();
          } else if (tagname.equalsIgnoreCase(APPOINTMENTS)) {
            appointmentTimes = new ArrayList<>();
          }
          break;
        case XmlPullParser.TEXT:
          text = parser.getText();
          break;
        case XmlPullParser.END_TAG:
          if (tagname.equalsIgnoreCase(SUBJECT)) {
            subject.setAppointmentTimes(appointmentTimes);
            subjectEvents.add(subject);
          } else if (tagname.equalsIgnoreCase(STUDENT)) {
            student = text;
          } else if (tagname.equalsIgnoreCase(APPOINTMENT)) {
            appointmentTimes.add(appointmentTime);
          } else if (tagname.equalsIgnoreCase(FROM)) {
            appointmentTime.setFrom(Long.valueOf(text));
          } else if (tagname.equalsIgnoreCase(TO)) {
            appointmentTime.setTo(Long.valueOf(text));
          } else if (tagname.equalsIgnoreCase(WHEN)) {
            appointmentTime.setWhen(Long.valueOf(text));
          } else if (tagname.equalsIgnoreCase(ID)) {
            appointmentTime.setId(Long.valueOf(text));
          } else if (tagname.equalsIgnoreCase(PRESENT)) {
            appointmentTime.setPresent(Boolean.valueOf(text));
          } else if (tagname.equalsIgnoreCase(WHERE)) {
            subject.setWhere(text);
          } else if (tagname.equalsIgnoreCase(SUBJECT_NAME)) {
            subject.setSubjectName(text);
          } else if (tagname.equalsIgnoreCase(SUBJECT_ID)) {
            subject.setId(Long.parseLong(text));
          } else if (tagname.equalsIgnoreCase(DESCRIPTION)) {
            subject.setDescription(text);
          } else if (tagname.equals(ListUserAttendanceIqRequest.ELEMENT)) {
            done = true;
          }
          break;
        default:
          break;
      }
    }
    return new ListUserAttendanceIqRequest(student, subjectEvents);
  }

  @Override
  public Class<? extends BaseSmartCampusIqRequest> getHandledIq() {
    return ListUserAttendanceIqRequest.class;
  }

}

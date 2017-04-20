package hu.unideb.smartcampus.shared.iq.provider;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.APPOINTMENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.APPOINTMENTS;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.DESCRIPTION;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.END_PERIOD;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.FROM;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.START_PERIOD;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.STUDENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.SUBJECT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.SUBJECT_NAME;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.TO;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.WHEN;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.WHERE;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.ID;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.CalendarSubjectsIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.AppointmentTimeIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;

/**
 * Calendar subjects provider.
 */
@SuppressWarnings({"PMD"})
public class CalendarSubjectsIqProvider
    extends BaseSmartCampusIqProvider<CalendarSubjectsIqRequest> {

  @Override
  public CalendarSubjectsIqRequest parse(XmlPullParser parser, int initialDepth) throws Exception {
    Long startPeriod = 0L;
    Long endPeriod = 0L;
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
          } else if (tagname.equalsIgnoreCase(WHERE)) {
            subject.setWhere(text);
          } else if (tagname.equalsIgnoreCase(SUBJECT_NAME)) {
            subject.setSubjectName(text);
          } else if (tagname.equalsIgnoreCase(DESCRIPTION)) {
            subject.setDescription(text);
          } else if (tagname.equalsIgnoreCase(START_PERIOD)) {
            startPeriod = Long.valueOf(text);
          } else if (tagname.equalsIgnoreCase(END_PERIOD)) {
            endPeriod = Long.valueOf(text);
          } else if (tagname.equals(CalendarSubjectsIqRequest.ELEMENT)) {
            done = true;
          } else if (tagname.equalsIgnoreCase(ID)){
            subject.setId(Long.parseLong(text));
          }
          break;
        default:
          break;
      }
    }
    return new CalendarSubjectsIqRequest(student, subjectEvents, startPeriod, endPeriod);
  }

  @Override
  public Class<? extends BaseSmartCampusIqRequest> getHandledIq() {
    return CalendarSubjectsIqRequest.class;
  }

}

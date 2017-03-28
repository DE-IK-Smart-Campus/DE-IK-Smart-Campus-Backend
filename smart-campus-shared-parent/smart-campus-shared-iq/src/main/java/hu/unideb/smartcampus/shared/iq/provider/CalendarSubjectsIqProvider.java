package hu.unideb.smartcampus.shared.iq.provider;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.APPOINTMENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.DESCRIPTION;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.FROM;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.STUDENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.SUBJECT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.SUBJECT_NAME;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.TO;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.WHEN;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.WHERE;

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

  private List<CalendarSubjectIqElement> subjectEvents;
  private List<AppointmentTimeIqElement> appointmentTimes;
  private String student;
  private boolean done = false;
  private CalendarSubjectIqElement subject;
  private AppointmentTimeIqElement appointmentTime;

  @Override
  public CalendarSubjectsIqRequest parse(XmlPullParser parser, int initialDepth) throws Exception {
    subjectEvents = new ArrayList<>();
    appointmentTimes = new ArrayList<>();
    int eventType = parser.getEventType();
    String text = "";
    while (!done) {
      eventType = parser.next();
      String tagname = parser.getName();
      switch (eventType) {
        case XmlPullParser.START_TAG:
          parseStartTag(tagname);
          break;
        case XmlPullParser.TEXT:
          text = parser.getText();
          break;
        case XmlPullParser.END_TAG:
          parseEndTag(text, tagname);
          break;
        default:
          break;
      }
    }
    return new CalendarSubjectsIqRequest(student, subjectEvents);
  }

  private void parseStartTag(String tagname) {
    if (tagname.equalsIgnoreCase(SUBJECT)) {
      subject = new CalendarSubjectIqElement();
    } else if (tagname.equalsIgnoreCase(APPOINTMENT)) {
      appointmentTime = new AppointmentTimeIqElement();
    }
  }

  private void parseEndTag(String text, String tagname) {
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
    } else if (tagname.equals(CalendarSubjectsIqRequest.ELEMENT)) {
      done = true;
    }
  }

  @Override
  public Class<? extends BaseSmartCampusIqRequest> getHandledIq() {
    return CalendarSubjectsIqRequest.class;
  }

}

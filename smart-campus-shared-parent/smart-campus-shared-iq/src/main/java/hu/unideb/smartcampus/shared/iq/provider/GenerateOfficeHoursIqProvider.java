package hu.unideb.smartcampus.shared.iq.provider;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.GenerateOfficeHoursIqFields.DAY;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.GenerateOfficeHoursIqFields.FROM;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.GenerateOfficeHoursIqFields.FROM_DATE;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.GenerateOfficeHoursIqFields.CREATED_HOURS;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.GenerateOfficeHoursIqFields.INSTRUCTOR_ID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.GenerateOfficeHoursIqFields.OFFICE_HOUR;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.GenerateOfficeHoursIqFields.TO;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.GenerateOfficeHoursIqFields.TO_DATE;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.GenerateOfficeHoursIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.OfficeHourIntervallIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.OfficeHourIqElement;

/**
 * Office hour generator provider.
 */
@SuppressWarnings({"PMD"})
public class GenerateOfficeHoursIqProvider
    extends BaseSmartCampusIqProvider<GenerateOfficeHoursIqRequest> {

  @Override
  public GenerateOfficeHoursIqRequest parse(XmlPullParser parser, int initialDepth)
      throws Exception {
    OfficeHourIqElement officeHour = new OfficeHourIqElement();
    Long instructorId = 0L;
    OfficeHourIntervallIqElement intervall = new OfficeHourIntervallIqElement();
    List<OfficeHourIqElement> officeHours = new ArrayList<>();
    Integer createdHours = 0;
    int eventType = parser.getEventType();
    String text = "";
    boolean done = false;
    while (!done) {
      eventType = parser.next();
      String tagname = parser.getName();
      switch (eventType) {
        case XmlPullParser.START_TAG:
          if (tagname.equalsIgnoreCase(OFFICE_HOUR)) {
            officeHour = new OfficeHourIqElement();
          }
          break;
        case XmlPullParser.TEXT:
          text = parser.getText();
          break;
        case XmlPullParser.END_TAG:
          if (tagname.equalsIgnoreCase(OFFICE_HOUR)) {
            officeHours.add(officeHour);
          } else if (tagname.equalsIgnoreCase(INSTRUCTOR_ID)) {
            instructorId = Long.valueOf(text);
          } else if (tagname.equalsIgnoreCase(DAY)) {
            officeHour.setDay(DayOfWeek.valueOf(text));
          } else if (tagname.equalsIgnoreCase(TO)) {
            officeHour.setTo(text);
          } else if (tagname.equalsIgnoreCase(FROM)) {
            officeHour.setFrom(text);
          } else if (tagname.equalsIgnoreCase(FROM_DATE)) {
            intervall.setFromDate(Long.valueOf(text));
          } else if (tagname.equalsIgnoreCase(TO_DATE)) {
            intervall.setToDate(Long.valueOf(text));
          } else if (tagname.equalsIgnoreCase(CREATED_HOURS)) {
            createdHours = Integer.valueOf(text);
          } else if (tagname.equals(GenerateOfficeHoursIqRequest.ELEMENT)) {
            done = true;
          }
          break;

        default:
          break;
      }
    }
    return GenerateOfficeHoursIqRequest.builder()
        .intervall(intervall)
        .officeHours(officeHours)
        .instructorId(instructorId)
        .createdHours(createdHours)
        .build();
  }

  @Override
  public Class<? extends BaseSmartCampusIqRequest> getHandledIq() {
    return GenerateOfficeHoursIqRequest.class;
  }

}

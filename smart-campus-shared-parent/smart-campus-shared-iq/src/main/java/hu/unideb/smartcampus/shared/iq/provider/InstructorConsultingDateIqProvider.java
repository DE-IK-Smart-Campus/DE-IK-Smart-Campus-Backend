package hu.unideb.smartcampus.shared.iq.provider;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.InstructorConsultingDatesIqRequestFields.CONSULTING_DATE;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.InstructorConsultingDatesIqRequestFields.CONSULTING_DATE_ID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.InstructorConsultingDatesIqRequestFields.FROM;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.InstructorConsultingDatesIqRequestFields.FROM_TO_DATE;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.InstructorConsultingDatesIqRequestFields.INSTRUCTORID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.InstructorConsultingDatesIqRequestFields.RESERVED_SUM;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.InstructorConsultingDatesIqRequestFields.TO;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIq;
import hu.unideb.smartcampus.shared.iq.request.InstructorConsultingDatesIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.ConsultingDateIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.FromToDateIqElement;;


/**
 * Instructor consulting date IQ provider.
 */
@SuppressWarnings({"PMD"})
public class InstructorConsultingDateIqProvider
    extends BaseSmartCampusIqProvider<InstructorConsultingDatesIqRequest> {

  @Override
  public InstructorConsultingDatesIqRequest parse(XmlPullParser parser, int initialDepth)
      throws Exception {
    List<ConsultingDateIqElement> consultingHours = new ArrayList<>();
    ConsultingDateIqElement consultingDate = new ConsultingDateIqElement();
    FromToDateIqElement fromToDate = new FromToDateIqElement();
    Integer reservedSum = 0;
    String text = "";
    String instructorId = "";
    int eventType = parser.getEventType();
    boolean done = false;
    while (!done) {
      eventType = parser.next();
      String tagname = parser.getName();
      switch (eventType) {
        case XmlPullParser.START_TAG:
          if (tagname.equalsIgnoreCase(CONSULTING_DATE)) {
            consultingDate = new ConsultingDateIqElement();
            fromToDate = new FromToDateIqElement();
          }
          if (tagname.equalsIgnoreCase(FROM_TO_DATE)) {
            fromToDate = new FromToDateIqElement();
          }
          break;
        case XmlPullParser.TEXT:
          text = parser.getText();
          break;
        case XmlPullParser.END_TAG:
          if (tagname.equalsIgnoreCase(CONSULTING_DATE)) {
            consultingHours.add(consultingDate);
          } else if (tagname.equalsIgnoreCase(CONSULTING_DATE_ID)) {
            consultingDate.setConsultingDateId(Long.valueOf(text));
          } else if (tagname.equalsIgnoreCase(FROM)) {
            fromToDate.setFrom(Long.valueOf(text));
          } else if (tagname.equalsIgnoreCase(TO)) {
            fromToDate.setTo(Long.valueOf(text));
          } else if (tagname.equalsIgnoreCase(FROM_TO_DATE)) {
            consultingDate.setFromToDates(fromToDate);
          } else if (tagname.equalsIgnoreCase(RESERVED_SUM)) {
            reservedSum = Integer.valueOf(text);
            consultingDate.setReservedSum(reservedSum);
          } else if (tagname.equalsIgnoreCase(INSTRUCTORID)) {
            instructorId = text;
          } else if (tagname.equals(InstructorConsultingDatesIqRequest.ELEMENT)) {
            done = true;
          }
          break;

        default:
          break;
      }
    }

    InstructorConsultingDatesIqRequest iq = new InstructorConsultingDatesIqRequest(instructorId,consultingHours);
    return iq;
  }

  @Override
  public Class<? extends BaseSmartCampusIq> getHandledIq() {
    return InstructorConsultingDatesIqRequest.class;
  }

}

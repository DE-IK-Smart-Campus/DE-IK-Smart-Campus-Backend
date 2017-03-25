package hu.unideb.smartcampus.shared.iq.provider;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.InstructorConsultingDatesIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.ConsultingDateIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.FromToDateIqElement;

/**
 * Instructor consulting date IQ provider.
 */
@SuppressWarnings({"PMD"})
public class InstructorConsultingDateIqProvider
    extends IQProvider<InstructorConsultingDatesIqRequest> {

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
    while (eventType != XmlPullParser.END_DOCUMENT) {
      String tagname = parser.getName();
      switch (eventType) {
        case XmlPullParser.START_TAG:
          if (tagname.equalsIgnoreCase("consultingDate")) {
            consultingDate = new ConsultingDateIqElement();
            fromToDate = new FromToDateIqElement();
          }
          if (tagname.equalsIgnoreCase("fromToDate")) {
            fromToDate = new FromToDateIqElement();
          }
          break;
        case XmlPullParser.TEXT:
          text = parser.getText();
          break;
        case XmlPullParser.END_TAG:
          if (tagname.equalsIgnoreCase("consultingDate")) {
            consultingHours.add(consultingDate);
          } else if (tagname.equalsIgnoreCase("consultingDateId")) {
            consultingDate.setConsultingDateId(Long.valueOf(text));
          } else if (tagname.equalsIgnoreCase("from")) {
            fromToDate.setFrom(Long.valueOf(text));
          } else if (tagname.equalsIgnoreCase("to")) {
            fromToDate.setTo(Long.valueOf(text));
          } else if (tagname.equalsIgnoreCase("fromToDate")) {
            consultingDate.setFromToDates(fromToDate);
          } else if (tagname.equalsIgnoreCase("reservedSum")) {
            reservedSum = Integer.valueOf(text);
            consultingDate.setReservedSum(reservedSum);
          } else if (tagname.equalsIgnoreCase("instructorId")) {
            instructorId = text;
          }
          break;

        default:
          break;
      }
      eventType = parser.next();
    }

    InstructorConsultingDatesIqRequest iq = new InstructorConsultingDatesIqRequest(consultingHours);
    iq.setInstructorId(instructorId);
    return iq;
  }

}

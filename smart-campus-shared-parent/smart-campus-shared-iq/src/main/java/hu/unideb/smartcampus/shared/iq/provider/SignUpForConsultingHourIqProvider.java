package hu.unideb.smartcampus.shared.iq.provider;

import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.SignUpForConsultingDateIqRequest;

/**
 * Instructor consulting date IQ provider.
 */
@SuppressWarnings({"PMD"})
public class SignUpForConsultingHourIqProvider
    extends IQProvider<SignUpForConsultingDateIqRequest> {

  private String userId;
  private Long consultingHourId;
  private String reason;
  private String duration;

  @Override
  public SignUpForConsultingDateIqRequest parse(XmlPullParser parser, int initialDepth)
      throws Exception {
    String text = "";
    int eventType = parser.getEventType();
    while (eventType != XmlPullParser.END_DOCUMENT) {
      String tagname = parser.getName();
      switch (eventType) {
        case XmlPullParser.START_TAG:
          break;
        case XmlPullParser.TEXT:
          text = parser.getText();
          break;
        case XmlPullParser.END_TAG:
          if (tagname.equalsIgnoreCase("userId")) {
            userId = text;
          } else if (tagname.equalsIgnoreCase("consultingHourId")) {
            consultingHourId = Long.valueOf(text);
          } else if (tagname.equalsIgnoreCase("reason")) {
            reason = text;
          } else if (tagname.equalsIgnoreCase("duration")) {
            duration = text;
          }
          break;
        default:
          break;
      }
      eventType = parser.next();
    }

    SignUpForConsultingDateIqRequest iq =
        new SignUpForConsultingDateIqRequest(userId, consultingHourId, reason, duration);
    return iq;
  }

}

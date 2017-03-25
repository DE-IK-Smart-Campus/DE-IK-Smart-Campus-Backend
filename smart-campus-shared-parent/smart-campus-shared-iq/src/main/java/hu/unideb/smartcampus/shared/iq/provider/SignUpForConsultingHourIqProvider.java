package hu.unideb.smartcampus.shared.iq.provider;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.SignUpForConsultingDateFields.CONSULTING_HOUR_ID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SignUpForConsultingDateFields.DURATION;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SignUpForConsultingDateFields.REASON;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SignUpForConsultingDateFields.USER_ID;

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
    boolean done = false;
    while (!done) {
      String tagname = parser.getName();
      eventType = parser.next();
      switch (eventType) {
        case XmlPullParser.START_TAG:
          break;
        case XmlPullParser.TEXT:
          text = parser.getText();
          break;
        case XmlPullParser.END_TAG:
          if (tagname.equalsIgnoreCase(USER_ID)) {
            userId = text;
          } else if (tagname.equalsIgnoreCase(CONSULTING_HOUR_ID)) {
            consultingHourId = Long.valueOf(text);
          } else if (tagname.equalsIgnoreCase(REASON)) {
            reason = text;
          } else if (tagname.equalsIgnoreCase(DURATION)) {
            duration = text;
          } else if (tagname.equalsIgnoreCase(SignUpForConsultingDateIqRequest.ELEMENT)) {
            done = true;
          }
          break;
        default:
          break;
      }
    }

    return new SignUpForConsultingDateIqRequest(userId, consultingHourId, reason, duration);
  }

}

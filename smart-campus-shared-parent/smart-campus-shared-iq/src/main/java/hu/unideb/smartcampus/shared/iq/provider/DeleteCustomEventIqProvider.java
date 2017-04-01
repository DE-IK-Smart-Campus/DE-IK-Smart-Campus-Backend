package hu.unideb.smartcampus.shared.iq.provider;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_ID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.STUDENT;

import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.DeleteCustomEventIqRequest;

/**
 * Delete custom event provider.
 */
@SuppressWarnings({"PMD"})
public class DeleteCustomEventIqProvider
    extends BaseSmartCampusIqProvider<DeleteCustomEventIqRequest> {

  private String student;
  private Long eventId;
  private boolean done;

  @Override
  public DeleteCustomEventIqRequest parse(XmlPullParser parser, int initialDepth) throws Exception {
    done = false;
    int eventType = parser.getEventType();
    String text = "";
    while (!done) {
      eventType = parser.next();
      String tagname = parser.getName();
      switch (eventType) {
        case XmlPullParser.START_TAG:
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
    return new DeleteCustomEventIqRequest(student, eventId);
  }

  private void parseEndTag(String text, String tagname) {
    if (tagname.equalsIgnoreCase(STUDENT)) {
      student = text;
    } else if (tagname.equalsIgnoreCase(EVENT_ID)) {
      eventId = Long.valueOf(text);
    } else if (tagname.equals(DeleteCustomEventIqRequest.ELEMENT)) {
      done = true;
    }
  }

  @Override
  public Class<? extends BaseSmartCampusIqRequest> getHandledIq() {
    return DeleteCustomEventIqRequest.class;
  }

}

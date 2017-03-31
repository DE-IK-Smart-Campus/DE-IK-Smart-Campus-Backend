package hu.unideb.smartcampus.shared.iq.provider;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.SaveSubjectsIcsIqFields.ICS;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SaveSubjectsIcsIqFields.STATUS_MESSAGE;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SaveSubjectsIcsIqFields.STUDENT;

import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.SaveSubjectsIcsIqRequest;

/**
 * Save subjects by ICS IQ provider.
 */
@SuppressWarnings({"PMD"})
public class SaveSubjectsIcsIqProvider extends BaseSmartCampusIqProvider<SaveSubjectsIcsIqRequest> {

  private String student;
  private String ics;
  private String statusMessage;
  boolean done = false;

  @Override
  public SaveSubjectsIcsIqRequest parse(XmlPullParser parser, int initialDepth) throws Exception {
    int eventType = parser.getEventType();
    String text = "";
    done = false;
    while (!done) {
      eventType = parser.next();
      String tagname = parser.getName();
      switch (eventType) {
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
    return new SaveSubjectsIcsIqRequest(student, ics, statusMessage);
  }

  private void parseEndTag(String text, String tagname) {
    if (tagname.equalsIgnoreCase(ICS)) {
      ics = text;
    } else if (tagname.equalsIgnoreCase(STUDENT)) {
      student = text;
    } else if (tagname.equalsIgnoreCase(STATUS_MESSAGE)) {
      statusMessage = text;
    } else if (tagname.equals(SaveSubjectsIcsIqRequest.ELEMENT)) {
      done = true;
    }
  }

  @Override
  public Class<? extends BaseSmartCampusIqRequest> getHandledIq() {
    return SaveSubjectsIcsIqRequest.class;
  }

}

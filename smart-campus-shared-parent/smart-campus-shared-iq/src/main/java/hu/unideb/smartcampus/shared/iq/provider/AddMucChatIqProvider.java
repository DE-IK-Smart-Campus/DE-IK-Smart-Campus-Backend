package hu.unideb.smartcampus.shared.iq.provider;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.AddMucChatIqField.ROOM;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.AddMucChatIqField.STUDENT;

import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.AddMucChatIqRequest;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;

/**
 * Add MUC chat provider.
 */
@SuppressWarnings({"PMD"})
public class AddMucChatIqProvider extends BaseSmartCampusIqProvider<AddMucChatIqRequest> {

  @Override
  public AddMucChatIqRequest parse(XmlPullParser parser, int initialDepth) throws Exception {
    String student = null;
    String muc = null;
    int eventType = parser.getEventType();
    String text = "";
    boolean done = false;
    while (!done) {
      eventType = parser.next();
      String tagname = parser.getName();
      switch (eventType) {
        case XmlPullParser.START_TAG:
          if (tagname.equalsIgnoreCase(STUDENT)) {

          }
          break;
        case XmlPullParser.TEXT:
          text = parser.getText();
          break;
        case XmlPullParser.END_TAG:
          if (tagname.equalsIgnoreCase(STUDENT)) {
            student = text;
          } else if (tagname.equalsIgnoreCase(ROOM)) {
            muc = text;
          } else if (tagname.equals(AddMucChatIqRequest.ELEMENT)) {
            done = true;
          }
          break;

        default:
          break;
      }
    }
    return AddMucChatIqRequest.builder()
        .student(student)
        .muc(muc)
        .build();
  }

  @Override
  public Class<? extends BaseSmartCampusIqRequest> getHandledIq() {
    return AddMucChatIqRequest.class;
  }

}

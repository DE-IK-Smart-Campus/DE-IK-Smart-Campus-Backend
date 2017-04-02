package hu.unideb.smartcampus.shared.iq.provider;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.AddUserChatIqFields.CHAT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.AddUserChatIqFields.STUDENT;

import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.AddUserChatIqRequest;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;

/**
 * Add user chat IQ provider.
 */
@SuppressWarnings({"PMD"})
public class AddUserChatIqProvider extends BaseSmartCampusIqProvider<AddUserChatIqRequest> {

  @Override
  public AddUserChatIqRequest parse(XmlPullParser parser, int initialDepth) throws Exception {
    String student = null;
    String chat = null;
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
          } else if (tagname.equalsIgnoreCase(CHAT)) {
            chat = text;
          } else if (tagname.equals(AddUserChatIqRequest.ELEMENT)) {
            done = true;
          }
          break;

        default:
          break;
      }
    }
    return AddUserChatIqRequest.builder()
        .student(student)
        .chat(chat)
        .build();
  }

  @Override
  public Class<? extends BaseSmartCampusIqRequest> getHandledIq() {
    return AddUserChatIqRequest.class;
  }

}

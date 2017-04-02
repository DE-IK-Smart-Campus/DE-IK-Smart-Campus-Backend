package hu.unideb.smartcampus.shared.iq.provider;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.UserChatListIqRequestFields.CHAT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.UserChatListIqRequestFields.STUDENT;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.UserChatListIqRequest;

/**
 * Chat listing provider.
 */
@SuppressWarnings({"PMD"})
public class UserChatListIqProvider extends BaseSmartCampusIqProvider<UserChatListIqRequest> {

  @Override
  public UserChatListIqRequest parse(XmlPullParser parser, int initialDepth) throws Exception {
    String student = null;
    List<String> chatList = new ArrayList<>();
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
            chatList.add(text);
          } else if (tagname.equals(UserChatListIqRequest.ELEMENT)) {
            done = true;
          }
          break;

        default:
          break;
      }
    }
    return UserChatListIqRequest.builder()
        .student(student)
        .chatList(chatList)
        .build();
  }

  @Override
  public Class<? extends BaseSmartCampusIqRequest> getHandledIq() {
    return UserChatListIqRequest.class;
  }

}

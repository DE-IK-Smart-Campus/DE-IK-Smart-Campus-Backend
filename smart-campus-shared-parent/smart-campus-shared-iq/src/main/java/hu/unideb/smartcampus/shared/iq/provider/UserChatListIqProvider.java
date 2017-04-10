package hu.unideb.smartcampus.shared.iq.provider;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.UserChatListIqRequestFields.CHAT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.UserChatListIqRequestFields.ROOM;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.UserChatListIqRequestFields.STUDENT;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListUserChatsIqRequest;

/**
 * Chat listing provider.
 */
@SuppressWarnings({"PMD"})
public class UserChatListIqProvider extends BaseSmartCampusIqProvider<ListUserChatsIqRequest> {

  @Override
  public ListUserChatsIqRequest parse(XmlPullParser parser, int initialDepth) throws Exception {
    String student = null;
    List<String> chatList = new ArrayList<>();
    List<String> mucChatList = new ArrayList<>();
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
          } else if (tagname.equalsIgnoreCase(ROOM)) {
            mucChatList.add(text);
          } else if (tagname.equals(ListUserChatsIqRequest.ELEMENT)) {
            done = true;
          }
          break;

        default:
          break;
      }
    }
    return ListUserChatsIqRequest.builder()
        .student(student)
        .chatList(chatList)
        .mucChatList(mucChatList)
        .build();
  }

  @Override
  public Class<? extends BaseSmartCampusIqRequest> getHandledIq() {
    return ListUserChatsIqRequest.class;
  }

}

package hu.unideb.smartcampus.shared.iq.provider;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.UserMucListIqRequestFields.ROOM;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.UserMucListIqRequestFields.STUDENT;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListUserMucIqRequest;

/**
 * MUC listing provider.
 */
@SuppressWarnings({"PMD"})
public class UserMucListIqProvider extends BaseSmartCampusIqProvider<ListUserMucIqRequest> {

  @Override
  public ListUserMucIqRequest parse(XmlPullParser parser, int initialDepth) throws Exception {
    String student = null;
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
          } else if (tagname.equalsIgnoreCase(ROOM)) {
            mucChatList.add(text);
          } else if (tagname.equals(ListUserMucIqRequest.ELEMENT)) {
            done = true;
          }
          break;

        default:
          break;
      }
    }
    return ListUserMucIqRequest.builder()
        .student(student)
        .mucChatList(mucChatList)
        .build();
  }

  @Override
  public Class<? extends BaseSmartCampusIqRequest> getHandledIq() {
    return ListUserMucIqRequest.class;
  }

}

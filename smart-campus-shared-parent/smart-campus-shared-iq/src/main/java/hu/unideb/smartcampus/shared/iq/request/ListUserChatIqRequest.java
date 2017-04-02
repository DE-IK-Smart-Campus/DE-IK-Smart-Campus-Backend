package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.UserChatListIqRequestFields.CHAT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.UserChatListIqRequestFields.CHATS;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.UserChatListIqRequestFields.STUDENT;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Chat listing IQ.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class ListUserChatIqRequest extends BaseSmartCampusIqRequest {

  /**
   * Element.
   */
  public static final String ELEMENT = "listUserChats";

  /**
   * Student's username.
   */
  private String student;

  /**
   * Student's chat list.
   */
  private List<String> chatList;

  /**
   * Default constructor.
   */
  public ListUserChatIqRequest() {
    super(ELEMENT);
    chatList = new ArrayList<>();
  }

  /**
   * Constructs.
   */
  @Builder
  public ListUserChatIqRequest(String student, List<String> chatList) {
    super(ELEMENT);
    this.student = student;
    this.chatList = chatList;
  }

  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    builder.append(tag(STUDENT, student));
    buildEvents(builder);
  }

  private void buildEvents(StringBuilder builder) {
    if (chatList != null && !chatList.isEmpty()) {
      builder.append(openTag(CHATS));
      for (String muc : chatList) {
        builder.append(tag(CHAT, muc));
      }
      builder.append(closeTag(CHATS));
    }
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }
}

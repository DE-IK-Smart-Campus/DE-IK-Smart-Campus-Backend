package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.UserChatListIqRequestFields.CHAT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.UserChatListIqRequestFields.CHATS;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.UserChatListIqRequestFields.ROOM;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.UserChatListIqRequestFields.ROOMS;
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
public class ListUserChatsIqRequest extends BaseSmartCampusIqRequest {

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
   * Student's muc chat list.
   */
  private List<String> mucChatList;

  /**
   * Default constructor.
   */
  public ListUserChatsIqRequest() {
    super(ELEMENT);
    chatList = new ArrayList<>();
    mucChatList = new ArrayList<>();
  }

  /**
   * Constructs.
   */
  @Builder
  public ListUserChatsIqRequest(String student, List<String> chatList, List<String> mucChatList) {
    super(ELEMENT);
    this.student = student;
    this.chatList = chatList;
    this.mucChatList = mucChatList;
  }

  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    builder.append(tag(STUDENT, student));
    buildSingleChats(builder);
    buildMucChats(builder);
  }

  private void buildMucChats(StringBuilder builder) {
    if (mucChatList != null) {
      builder.append(openTag(ROOMS));
      for (String muc : mucChatList) {
        builder.append(tag(ROOM, muc));
      }
      builder.append(closeTag(ROOMS));
    }
  }

  private void buildSingleChats(StringBuilder builder) {
    if (chatList != null) {
      builder.append(openTag(CHATS));
      for (String chat : chatList) {
        builder.append(tag(CHAT, chat));
      }
      builder.append(closeTag(CHATS));
    }
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }
}

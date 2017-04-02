package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.UserMucListIqRequestFields.ROOM;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.UserMucListIqRequestFields.ROOMS;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.UserMucListIqRequestFields.STUDENT;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * MUC listing IQ.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class UserMucListIqRequest extends BaseSmartCampusIqRequest {

  /**
   * Element.
   */
  public static final String ELEMENT = "listUserMucRooms";

  /**
   * Student's username.
   */
  private String student;

  /**
   * Student's muc chat list.
   */
  private List<String> mucChatList;

  /**
   * Default constructor.
   */
  public UserMucListIqRequest() {
    super(ELEMENT);
    mucChatList = new ArrayList<>();
  }

  /**
   * Constructs.
   */
  @Builder
  public UserMucListIqRequest(String student, List<String> mucChatList) {
    super(ELEMENT);
    this.student = student;
    this.mucChatList = mucChatList;
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
    if (mucChatList != null && !mucChatList.isEmpty()) {
      builder.append(openTag(ROOMS));
      for (String muc : mucChatList) {
        builder.append(tag(ROOM, muc));
      }
      builder.append(closeTag(ROOMS));
    }
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }
}

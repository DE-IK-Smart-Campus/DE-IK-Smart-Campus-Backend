package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.AddUserChatIqFields.CHAT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.AddUserChatIqFields.STUDENT;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Add user chat IQ.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AddUserChatIqRequest extends BaseSmartCampusIqRequest {

  /**
   * Element.
   */
  public static final String ELEMENT = "addUserChat";

  /**
   * Student's username.
   */
  private String student;

  /**
   * Student's chat list.
   */
  private String chat;

  /**
   * Default constructor.
   */
  public AddUserChatIqRequest() {
    super(ELEMENT);
  }

  /**
   * Constructs.
   */
  @Builder
  public AddUserChatIqRequest(String student, String chat) {
    super(ELEMENT);
    this.student = student;
    this.chat = chat;
  }

  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    builder.append(tag(STUDENT, student));
    builder.append(tag(CHAT, chat));
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }
}

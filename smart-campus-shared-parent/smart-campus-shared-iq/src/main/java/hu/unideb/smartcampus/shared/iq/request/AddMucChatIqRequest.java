package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.AddMucChatIqField.ROOM;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.AddMucChatIqField.STUDENT;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Add muc chat IQ.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AddMucChatIqRequest extends BaseSmartCampusIqRequest {

  /**
   * Element.
   */
  public static final String ELEMENT = "addMucChat";

  /**
   * Student's username.
   */
  private String student;

  /**
   * Student's chat list.
   */
  private String muc;

  /**
   * Default constructor.
   */
  public AddMucChatIqRequest() {
    super(ELEMENT);
  }

  /**
   * Constructs.
   */
  @Builder
  public AddMucChatIqRequest(String student, String muc) {
    super(ELEMENT);
    this.student = student;
    this.muc = muc;
  }

  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    builder.append(tag(STUDENT, student));
    builder.append(tag(ROOM, muc));
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }
}

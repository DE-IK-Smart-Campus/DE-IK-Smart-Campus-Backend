package hu.unideb.smartcampus.shared.requestmessages;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Example message.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ExampleRequest extends BaseRequest {

  /**
   * Message type.
   */
  private static final String MESSAGE_TYPE = "ExampleProcessMessage";

  /**
   * Example field.
   */
  private String example;

  /**
   * Constructor for Jackson.
   */
  public ExampleRequest() {
    super(MESSAGE_TYPE);
  }

  /**
   * Constructs an example request.
   */
  @Builder
  public ExampleRequest(final String messageType, final String example) {
    super(messageType);
    this.example = example;
  }


}

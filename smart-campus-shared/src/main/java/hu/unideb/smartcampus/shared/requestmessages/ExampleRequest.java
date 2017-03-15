package hu.unideb.smartcampus.shared.requestmessages;

import lombok.Builder;
import lombok.Data;

/**
 * Example message.
 *
 */
@Data
public class ExampleRequest extends BaseRequest {

  /**
   * Message type.
   */
  private static final String MESSAGE_TYPE = "ExampleProcessMessage";

  /**
   * Example field.
   */
  private final String example;

  /**
   * Constructs an example request.
   */
  @Builder
  public ExampleRequest(final String messageType, final String example) {
    super(messageType);
    this.example = example;
  }


}

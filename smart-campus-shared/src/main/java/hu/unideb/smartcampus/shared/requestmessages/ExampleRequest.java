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
   * Example field.
   */
  private String example;

  /**
   * Constructs an example request.
   */
  @Builder
  public ExampleRequest(final String messageType, final String example) {
    super(messageType);
    this.example = example;
  }


}

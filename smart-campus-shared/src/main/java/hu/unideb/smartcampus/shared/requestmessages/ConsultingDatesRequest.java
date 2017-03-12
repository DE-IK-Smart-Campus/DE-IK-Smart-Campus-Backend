package hu.unideb.smartcampus.shared.requestmessages;

import lombok.Builder;
import lombok.Data;

/**
 * A message type.
 *
 */
@Data
public class ConsultingDatesRequest extends BaseRequest {

  /**
   * Name.
   */
  private final String name;

  /**
   * Constructs an instance.
   */
  @Builder
  public ConsultingDatesRequest(final String messageType, final String name) {
    super(messageType);
    this.name = name;
  }


}

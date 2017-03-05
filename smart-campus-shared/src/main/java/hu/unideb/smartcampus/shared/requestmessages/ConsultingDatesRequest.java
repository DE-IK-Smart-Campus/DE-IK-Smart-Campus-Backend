package hu.unideb.smartcampus.shared.requestmessages;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A message type.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ConsultingDatesRequest extends BaseRequest {

  /**
   * Name.
   */
  private String name;

  /**
   * Constructs an instance.
   */
  @Builder
  public ConsultingDatesRequest(final String messageType, final String name) {
    super(messageType);
    this.name = name;
  }


}

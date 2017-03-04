package hu.unideb.smartcampus.shared.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A message type.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ConsultingDatesProcessMessage extends BaseMessage implements BaseMessageType {

  /**
   * Name.
   */
  private String name;
}

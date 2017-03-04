package hu.unideb.smartcampus.shared.message;

import lombok.Data;

/**
 * Base msg.
 *
 */
@Data
public class BaseMessage {
  /**
   * Message type.
   */
  protected String messageType;
}

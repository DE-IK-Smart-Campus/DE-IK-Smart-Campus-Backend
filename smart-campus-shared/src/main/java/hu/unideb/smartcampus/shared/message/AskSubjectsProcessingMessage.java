package hu.unideb.smartcampus.shared.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Send this message to retrivie the given user's subjects.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AskSubjectsProcessingMessage extends BaseMessage implements BaseMessageType {

  /**
   * User id.
   */
  private Long userId;
}

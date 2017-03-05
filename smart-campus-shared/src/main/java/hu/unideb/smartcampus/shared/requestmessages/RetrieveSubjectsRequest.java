package hu.unideb.smartcampus.shared.requestmessages;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Send this message to retrivie the given user's subjects.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RetrieveSubjectsRequest extends BaseRequest {

  /**
   * Message type.
   */
  private static final String MESSAGE_TYPE = "AskSubjectsProcessMessage";

  /**
   * User id.
   */
  private Long userId;

  /**
   * Constructor for Jackson.
   */
  public RetrieveSubjectsRequest() {
    super(MESSAGE_TYPE);
  }

  /**
   * Constructs an instance.
   */
  public RetrieveSubjectsRequest(final String messageType, final Long userId) {
    super(messageType);
    this.userId = userId;
  }


}

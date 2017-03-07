package hu.unideb.smartcampus.shared.requestmessages;

import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;
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
   * User id.
   */
  private Long userId;

  /**
   * Constructor for Jackson.
   */
  public RetrieveSubjectsRequest() {
    super(RequestMessagesConstants.RETRIEVE_SUBJECTS_REQUEST);
  }

  /**
   * Constructs an instance.
   */
  public RetrieveSubjectsRequest(final String messageType, final Long userId) {
    super(messageType);
    this.userId = userId;
  }


}

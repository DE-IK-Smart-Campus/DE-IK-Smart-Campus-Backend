package hu.unideb.smartcampus.shared.requestmessages;

import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;
import lombok.Builder;
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
   * User JID.
   */
  private String userId;

  /**
   * Constructor for Jackson.
   */
  public RetrieveSubjectsRequest() {
    super(RequestMessagesConstants.RETRIEVE_SUBJECTS_REQUEST);
  }

  /**
   * Constructs an instance.
   */
  @Builder
  public RetrieveSubjectsRequest(final String messageType, final String userId) {
    super(messageType);
    this.userId = userId;
  }


}

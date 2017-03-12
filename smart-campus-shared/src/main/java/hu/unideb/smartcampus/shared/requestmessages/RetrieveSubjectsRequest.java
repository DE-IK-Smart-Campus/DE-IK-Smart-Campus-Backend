package hu.unideb.smartcampus.shared.requestmessages;

import lombok.Builder;
import lombok.Data;

/**
 * Send this message to retrivie the given user's subjects.
 *
 */
@Data
public class RetrieveSubjectsRequest extends BaseRequest {

  /**
   * User JID.
   */
  private final String userId;


  /**
   * Constructs an instance.
   */
  @Builder
  public RetrieveSubjectsRequest(final String messageType, final String userId) {
    super(messageType);
    this.userId = userId;
  }


}

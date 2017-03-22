package hu.unideb.smartcampus.shared.requestmessages;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;

/**
 * Send this message to retrivie the given user's subjects.
 *
 */
@Data
@JsonDeserialize(builder = RetrieveSubjectsRequest.RetrieveSubjectsRequestBuilder.class)
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

  /**
   * Builder.
   *
   */
  @JsonPOJOBuilder(withPrefix = "")
  public static class RetrieveSubjectsRequestBuilder {

  }
}

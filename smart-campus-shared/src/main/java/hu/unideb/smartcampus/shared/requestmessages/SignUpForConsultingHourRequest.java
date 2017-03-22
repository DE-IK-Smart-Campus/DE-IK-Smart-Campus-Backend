package hu.unideb.smartcampus.shared.requestmessages;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;

/**
 * Sign up for consulting hour request.
 *
 */
@Data
@JsonDeserialize(
    builder = SignUpForConsultingHourRequest.SignUpForConsultingHourRequestBuilder.class)
public class SignUpForConsultingHourRequest extends BaseRequest {

  /**
   * User's JID.
   */
  private final String userId;

  /**
   * Consulting hour id.
   */
  private final Long consultingHourId;

  /**
   * Reason of consulting.
   */
  private final String reason;

  /**
   * Duration of consulting.
   */
  private final String duration;

  /**
   * Constructs a SignUpForConsultingHourRequest instance.
   */
  @Builder
  public SignUpForConsultingHourRequest(final String messageType, final Long consultingHourId,
      final String reason, final String duration, final String userId) {
    super(messageType);
    this.consultingHourId = consultingHourId;
    this.reason = reason;
    this.duration = duration;
    this.userId = userId;
  }

  /**
   * Builder.
   *
   */
  @JsonPOJOBuilder(withPrefix = "")
  public static final class SignUpForConsultingHourRequestBuilder {
  }

}

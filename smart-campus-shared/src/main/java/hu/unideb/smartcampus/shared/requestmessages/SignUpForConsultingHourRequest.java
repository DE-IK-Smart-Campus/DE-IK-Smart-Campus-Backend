package hu.unideb.smartcampus.shared.requestmessages;

import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Sign up for consulting hour request.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SignUpForConsultingHourRequest extends BaseRequest {

  /**
   * User's id.
   */
  private Long userId;

  /**
   * Consulting hour id.
   */
  private Long consultingHourId;

  /**
   * Reason of consulting.
   */
  private String reason;

  /**
   * Duration of consulting.
   */
  private String duration;

  /**
   * Constructor for Jackson.
   */
  public SignUpForConsultingHourRequest() {
    super(RequestMessagesConstants.SIGN_UP_FOR_CONSULTING_HOUR_REQUEST);
  }

  /**
   * Constructs a SignUpForConsultingHourRequest instance.
   */
  @Builder
  public SignUpForConsultingHourRequest(final String messageType, final Long consultingHourId,
      final String reason, final String duration, final Long userId) {
    super(messageType);
    this.consultingHourId = consultingHourId;
    this.reason = reason;
    this.duration = duration;
    this.userId = userId;
  }

}

package hu.unideb.smartcampus.service.api.domain.response.wrapper;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Sign up for consulting hour response wrapper.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SignUpForConsultingHourWrapper extends BaseWrapper {

  /**
   * Status.
   */
  private String status;

  /**
   * Constructs a SignUpForConsultingHourWrapper instance.
   */
  @Builder
  public SignUpForConsultingHourWrapper(final String messageType, final String status) {
    super(messageType);
    this.status = status;
  }



}

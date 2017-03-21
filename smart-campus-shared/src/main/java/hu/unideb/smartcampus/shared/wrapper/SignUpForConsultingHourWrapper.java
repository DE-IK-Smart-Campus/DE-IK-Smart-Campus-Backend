package hu.unideb.smartcampus.shared.wrapper;

import lombok.Builder;
import lombok.Data;

/**
 * Sign up for consulting hour response wrapper.
 *
 */
@Data
public class SignUpForConsultingHourWrapper extends BaseWrapper {

  /**
   * Status.
   */
  private final String status;

  /**
   * Constructs a SignUpForConsultingHourWrapper instance.
   */
  @Builder
  public SignUpForConsultingHourWrapper(final String messageType, final String status) {
    super(messageType);
    this.status = status;
  }



}

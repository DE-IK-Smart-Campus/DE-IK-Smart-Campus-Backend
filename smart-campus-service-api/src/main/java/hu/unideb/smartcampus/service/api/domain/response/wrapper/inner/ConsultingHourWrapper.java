package hu.unideb.smartcampus.service.api.domain.response.wrapper.inner;

import lombok.Builder;
import lombok.Data;

/**
 * Consulting hour wrapper.
 */
@Data
public class ConsultingHourWrapper {

  /**
   * Consulting hour id.
   */
  private final Long consultingHourId;

  /**
   * From to date.
   */
  private final FromToDateWrapper fromToDates;

  /**
   * Sum of reserved studenst.
   */
  private final Integer reservedSum;

  /**
   * Constructs a ConsultingHourWrapper.
   */
  @Builder
  public ConsultingHourWrapper(final Long consultingHourId, final FromToDateWrapper fromToDates,
      final Integer reservedSum) {
    this.consultingHourId = consultingHourId;
    this.fromToDates = fromToDates;
    this.reservedSum = reservedSum;
  }



}

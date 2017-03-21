package hu.unideb.smartcampus.shared.wrapper.inner;

import lombok.Builder;
import lombok.Data;

/**
 * Consulting hour wrapper.
 */
@Data
public class ConsultingDateWrapper {

  /**
   * Consulting date id.
   */
  private final Long consultingDateId;

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
  public ConsultingDateWrapper(final Long consultingHourId, final FromToDateWrapper fromToDates,
      final Integer reservedSum) {
    this.consultingDateId = consultingHourId;
    this.fromToDates = fromToDates;
    this.reservedSum = reservedSum;
  }



}

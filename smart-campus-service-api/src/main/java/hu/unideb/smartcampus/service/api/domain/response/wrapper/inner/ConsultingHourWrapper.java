package hu.unideb.smartcampus.service.api.domain.response.wrapper.inner;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Consulting hour wrapper.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ConsultingHourWrapper {

  /**
   * Consulting hour id.
   */
  private Long consultingHourId;

  /**
   * From to date.
   */
  private FromToDateWrapper fromToDates;

  /**
   * Sum of reserved studenst.
   */
  private Integer reservedSum;

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

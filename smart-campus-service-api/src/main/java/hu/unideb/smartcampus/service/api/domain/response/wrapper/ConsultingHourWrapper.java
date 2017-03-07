package hu.unideb.smartcampus.service.api.domain.response.wrapper;

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
  private FromToDate fromToDates;

  /**
   * Sum of reserved studenst.
   */
  private Integer reservedSum;

  /**
   * Constructs a ConsultingHourWrapper.
   */
  @Builder
  public ConsultingHourWrapper(final Long consultingHourId, final FromToDate fromToDates,
      final Integer reservedSum) {
    this.consultingHourId = consultingHourId;
    this.fromToDates = fromToDates;
    this.reservedSum = reservedSum;
  }



}

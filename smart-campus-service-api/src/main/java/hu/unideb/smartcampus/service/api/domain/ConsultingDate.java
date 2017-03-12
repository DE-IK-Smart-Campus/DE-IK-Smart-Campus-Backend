package hu.unideb.smartcampus.service.api.domain;

import lombok.Builder;
import lombok.Data;

/**
 * Consulting date.
 *
 */
@Data
public class ConsultingDate {

  /**
   * Date of the consulting hours.
   */
  private final String date;

  /**
   * Constructs consulting date.
   */
  @Builder
  public ConsultingDate(final String date) {
    this.date = date;
  }

}

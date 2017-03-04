package hu.unideb.smartcampus.service.api.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Consulting date.
 *
 */
@Data
@NoArgsConstructor
public class ConsultingDate {

  /**
   * Date of the consulting hours.
   */
  private String date;

  /**
   * Constructs consulting date.
   */
  @Builder
  public ConsultingDate(final String date) {
    this.date = date;
  }

}

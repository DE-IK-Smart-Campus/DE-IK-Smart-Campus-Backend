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
  private String dateInString;

  /**
   * From to date entity.
   */
  private FromToDate fromToDate;

  /**
   * Sum of students.
   */
  private Integer sum;

  /**
   * Constructs consulting date.
   */
  @Builder
  public ConsultingDate(final String date, final FromToDate fromToDate, final Integer sum) {
    this.dateInString = date;
    this.fromToDate = fromToDate;
    this.sum = sum;
  }


}

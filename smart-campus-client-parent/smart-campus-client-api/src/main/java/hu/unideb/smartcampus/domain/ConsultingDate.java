package hu.unideb.smartcampus.domain;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

/**
 * Consulting date.
 */
@Data
public class ConsultingDate {

  /**
   * From date.
   */
  private Timestamp from;

  /**
   * To date.
   */
  private Timestamp to;

  /**
   * Sum of students.
   */
  private Integer sum;

  /**
   * Constructs consulting date.
   */
  @Builder
  public ConsultingDate(final Timestamp from, final Timestamp to, final Integer sum) {
    this.from = from;
    this.to = to;
    this.sum = sum;
  }
}

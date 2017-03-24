package hu.unideb.smartcampus.service.api.domain;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * From to date domain.
 *
 */
@Data
@NoArgsConstructor
public class FromToDate {

  /**
   * From date.
   */
  private Timestamp fromDate;

  /**
   * To date.
   */
  private Timestamp toDate;

  /**
   * Constructs a FromToDate instance.
   */
  @Builder
  public FromToDate(final Timestamp fromDate, final Timestamp toDate) {
    this.fromDate = fromDate;
    this.toDate = toDate;
  }

}

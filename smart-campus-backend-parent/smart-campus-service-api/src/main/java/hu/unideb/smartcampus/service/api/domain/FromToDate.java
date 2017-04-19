package hu.unideb.smartcampus.service.api.domain;

import java.time.LocalDateTime;

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
  private LocalDateTime fromDate;

  /**
   * To date.
   */
  private LocalDateTime toDate;

  /**
   * Constructs a FromToDate instance.
   */
  @Builder
  public FromToDate(final LocalDateTime fromDate, final LocalDateTime toDate) {
    this.fromDate = fromDate;
    this.toDate = toDate;
  }

}

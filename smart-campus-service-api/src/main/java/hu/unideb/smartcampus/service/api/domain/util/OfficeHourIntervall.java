package hu.unideb.smartcampus.service.api.domain.util;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

/**
 * Class which indicates the office hours intervall within the semester.
 *
 */
@Data
public class OfficeHourIntervall {

  /**
   * Office hour intervall start.
   */
  private final LocalDate fromDate;

  /**
   * Office hour intervall end.
   */
  private final LocalDate toDate;

  /**
   * Constructs an OfficeHourIntervall instance.
   */
  @Builder
  public OfficeHourIntervall(final LocalDate fromDate, final LocalDate toDate) {
    this.fromDate = fromDate;
    this.toDate = toDate;
  }
}

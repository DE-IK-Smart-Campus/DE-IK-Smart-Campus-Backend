package hu.unideb.smartcampus.shared.util;

import lombok.Builder;
import lombok.Data;

/**
 * Inner class.
 *
 */
@Data
public class DateHelper {

  /**
   * Year.
   */
  private final Integer year;

  /**
   * Month.
   */
  private final Integer month;

  /**
   * Day.
   */
  private final Integer day;

  /**
   * Hour.
   */
  private final Integer hour;

  /**
   * Minute.
   */
  private final Integer minute;

  /**
   * Second.
   */
  private final Integer second;

  /**
   * Constructs a DateHelper instance.
   */
  @Builder
  public DateHelper(final Integer year, final Integer month, final Integer day, final Integer hour,
      final Integer minute, final Integer second) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minute = minute;
    this.second = second;
  }


}

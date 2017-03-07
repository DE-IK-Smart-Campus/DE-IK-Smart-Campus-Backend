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
  private Integer year;

  /**
   * Month.
   */
  private Integer month;

  /**
   * Day.
   */
  private Integer day;

  /**
   * Hour.
   */
  private Integer hour;

  /**
   * Minute.
   */
  private Integer minute;

  /**
   * Second.
   */
  private Integer second;

  /**
   * Constructs a DateHelper instance.
   */
  @Builder
  public DateHelper(Integer year, Integer month, Integer day, Integer hour, Integer minute,
      Integer second) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minute = minute;
    this.second = second;
  }


}

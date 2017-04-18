package hu.unideb.smartcampus.shared.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * Date util.
 */
public class DateUtil {

  private static final ZoneOffset HUNGARIAN_OFFSET = ZoneOffset.ofHours(2);

  /**
   * Get LocalDate by Long in epoch second.
   *
   * @param period date in epoch second.
   * @return date represented by long.
   */
  public static LocalDate getInLocalDateByEpochSecond(Long period) {
    return Instant.ofEpochSecond(period).atZone(HUNGARIAN_OFFSET).toLocalDate();
  }

  /**
   * Get LocalDateTime by Long in epoch second.
   *
   * @param period date in epoch second.
   * @return date time represented by long.
   */
  public static LocalDateTime getInLocalDateTimeByEpochSecond(Long period) {
    return Instant.ofEpochSecond(period).atZone(HUNGARIAN_OFFSET).toLocalDateTime();
  }

  /**
   * Get LocalTime by Long in epoch second.
   *
   * @param period date in epoch second.
   * @return time represented by long.
   */
  public static LocalTime getInLocalTimeByEpochSecond(Long period) {
    return Instant.ofEpochSecond(period).atZone(HUNGARIAN_OFFSET).toLocalTime();
  }

  /**
   * Get LocalDate in epoch long.
   *
   * @param date local date.
   * @return local date in epoch long.
   */
  public static Long getInEpochLongByLocalDate(LocalDate date) {
    return date.atStartOfDay().toEpochSecond(HUNGARIAN_OFFSET);
  }

  /**
   * Get LocalDateTime in epoch long.
   *
   * @param dateTime date time.
   * @return date time in epoch long.
   */
  public static Long getInEpochLongByLocalDateTime(LocalDateTime dateTime) {
    return dateTime.toEpochSecond(HUNGARIAN_OFFSET);
  }

}

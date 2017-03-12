package hu.unideb.smartcampus.persistence.util;

import java.sql.Timestamp;
import java.util.Calendar;

import hu.unideb.smartcampus.persistence.entity.FromToDateEmbeddedEntity;
import hu.unideb.smartcampus.shared.util.DateHelper;
import lombok.experimental.UtilityClass;

/**
 * Util class for test.
 *
 */
@UtilityClass
public class FromToDateUtil {

  /**
   * Creates an entity to tests.
   */
  public FromToDateEmbeddedEntity createEntity(DateHelper fromDate, DateHelper toDate) {
    Calendar from = createCalenderWithGivenOptions(fromDate);
    Calendar to = createCalenderWithGivenOptions(toDate);
    return FromToDateEmbeddedEntity.builder().fromDate(createTimestampWithZeroNanos(from))
        .toDate(createTimestampWithZeroNanos(to)).build();
  }

  private Calendar createCalenderWithGivenOptions(DateHelper date) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(date.getYear(), date.getMonth(), date.getDay(), date.getHour(), date.getMinute(),
        date.getSecond());
    return calendar;
  }

  private Timestamp createTimestampWithZeroNanos(Calendar calendar) {
    Timestamp timestamp = new Timestamp(calendar.getTime().getTime());
    timestamp.setNanos(0);
    return timestamp;
  }

}

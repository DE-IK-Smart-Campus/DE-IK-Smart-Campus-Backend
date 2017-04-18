package hu.unideb.smartcampus.service.api.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

/**
 * Semester util for deciding period.
 */
@Component
public class SemesterUtil {

  public static final LocalDate FIRST_SEMESTER_END = LocalDate.now()
      .with(ChronoField.MONTH_OF_YEAR, 12)
      .with(ChronoField.DAY_OF_MONTH, 31);

  public static final LocalDate FIRST_SEMESTER_START = LocalDate.now()
      .with(ChronoField.MONTH_OF_YEAR, 9)
      .with(ChronoField.DAY_OF_MONTH, 1);

  public static final LocalDate SECOND_SEMESTER_END = LocalDate.now()
      .with(ChronoField.MONTH_OF_YEAR, 5)
      .with(ChronoField.DAY_OF_MONTH, 31);

  public static final LocalDate SECOND_SEMESTER_START = LocalDate.now()
      .with(ChronoField.MONTH_OF_YEAR, 2)
      .with(ChronoField.DAY_OF_MONTH, 1);

  private static final ZoneOffset ZERO_HOUR_OFFSET = ZoneOffset.ofHours(0);

  private static final String FIRST_SEMESTER = "1";

  private static final String SEMESTER_SPLIT = "/";

  private static final int SEMESTER_START_INDEX = 0;

  private static final int SEMESTER_INDEX = 2;

  /**
   * Get end period by semester.
   * 
   * @param semester semester in string.
   * @return the end date.
   */
  public LocalDate getEndPeriod(String semester) {
    LocalDate result;
    String[] splittedSemester = semester.split(SEMESTER_SPLIT);
    String semesterNumber = splittedSemester[SEMESTER_INDEX];
    String year = splittedSemester[SEMESTER_START_INDEX];
    if (isFirstSemesterByString(semesterNumber)) {
      result = LocalDate.of(Integer.valueOf(year), Month.DECEMBER, 31);
    } else {
      result = LocalDate.of(Integer.valueOf(year) + 1, Month.MAY, 31);
    }
    return result;
  }

  /**
   * Get end period in long.
   * 
   * @return the end date in Long.
   */
  public Long getEndPeriodInLong() {
    if (isSecondSemesterByNow()) {
      return SECOND_SEMESTER_END.atStartOfDay().toEpochSecond(ZERO_HOUR_OFFSET);
    } else {
      return FIRST_SEMESTER_END.atStartOfDay().toEpochSecond(ZERO_HOUR_OFFSET);
    }
  }

  /**
   * Get the start period.
   * 
   * @param semester semester in string.
   * @return the start date.
   */
  public LocalDate getStartPeriod(String semester) {
    LocalDate result;
    String[] splittedSemester = semester.split(SEMESTER_SPLIT);
    String semesterNumber = splittedSemester[SEMESTER_INDEX];
    String year = splittedSemester[SEMESTER_START_INDEX];
    if (isFirstSemesterByString(semesterNumber)) {
      result = LocalDate.of(Integer.valueOf(year), Month.SEPTEMBER, 1);
    } else {
      result = LocalDate.of(Integer.valueOf(year) + 1, Month.FEBRUARY, 1);
    }
    return result;
  }

  /**
   * Get start period in long.
   * 
   * @return the start date in Long.
   */
  public Long getStartPeriodInLong() {
    if (isSecondSemesterByNow()) {
      return SECOND_SEMESTER_START.atStartOfDay().toEpochSecond(ZERO_HOUR_OFFSET);
    } else {
      return FIRST_SEMESTER_START.atStartOfDay().toEpochSecond(ZERO_HOUR_OFFSET);
    }
  }

  private boolean isFirstSemesterByString(String semesterNumber) {
    return FIRST_SEMESTER.equals(semesterNumber);
  }

  private boolean isSecondSemesterByNow() {
    LocalDate now = LocalDate.now();
    long fromFebruary = ChronoUnit.DAYS.between(now, SECOND_SEMESTER_START);
    long toMay = ChronoUnit.DAYS.between(now, SECOND_SEMESTER_END);
    return fromFebruary <= 0 && 0 <= toMay;
  }
}

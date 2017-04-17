package hu.unideb.smartcampus.service.api.util;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.stereotype.Component;

/**
 * Semester util for deciding period.
 */
@Component
public class SemesterUtil {

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
    if (isFirstSemester(semesterNumber)) {
      result = LocalDate.of(Integer.valueOf(year), Month.DECEMBER, 31);
    } else {
      result = LocalDate.of(Integer.valueOf(year) + 1, Month.MAY, 31);
    }
    return result;
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
    if (isFirstSemester(semesterNumber)) {
      result = LocalDate.of(Integer.valueOf(year), Month.SEPTEMBER, 1);
    } else {
      result = LocalDate.of(Integer.valueOf(year) + 1, Month.FEBRUARY, 1);
    }
    return result;
  }

  private boolean isFirstSemester(String semesterNumber) {
    return FIRST_SEMESTER.equals(semesterNumber);
  }
}

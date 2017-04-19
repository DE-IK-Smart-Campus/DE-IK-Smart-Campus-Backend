package hu.unideb.smartcampus.shared.test.property;

import hu.unideb.smartcampus.shared.util.DateHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * User test properties.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InstructorTestProperty {

  /**
   * Id of the admin user.
   */
  public static final long INSTRUCTOR_ID = 1L;

  /**
   * Username of the admin user.
   */
  public static final String NAME = "instructor";

  /**
   * Subject id.
   */
  public static final Long SUBJECT_ID = 1L;

  /**
   * Subject name.
   */
  public static final String SUBJECT_NAME = "AI";

  /**
   * Consulting date Friday ID.
   */
  public static final long CONSULTING_DATE_FRIDAY_ID = 1L;

  /**
   * Consulting date Friday.
   */
  public static final String CONSULTING_DATE_FRIDAY = "Friday 14-16";

  /**
   * Consulting date Monday ID.
   */
  public static final long CONSULTING_DATE_MONDAY_ID = 2L;

  /**
   * Consulting date Monday.
   */
  public static final String CONSULTING_DATE_MONDAY = "Monday 08-10";

  /**
   * Monday start date.
   */
  public static final DateHelper MONDAY_START_DATE =
      DateHelper.builder().year(2017).month(3).day(6).hour(8).minute(0).second(0).build();

  /**
   * Monday end date.
   */
  public static final DateHelper MONDAY_END_DATE =
      DateHelper.builder().year(2017).month(3).day(6).hour(10).minute(0).second(0).build();

  /**
   * Friday start date.
   */
  public static final DateHelper FRIDAY_START_DATE =
      DateHelper.builder().year(2017).month(3).day(10).hour(14).minute(0).second(0).build();

  /**
   * Friday end date.
   */
  public static final DateHelper FRIDAY_END_DATE =
      DateHelper.builder().year(2017).month(3).day(10).hour(16).minute(0).second(0).build();

}

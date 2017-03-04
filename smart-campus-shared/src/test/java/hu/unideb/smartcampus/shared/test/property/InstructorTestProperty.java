package hu.unideb.smartcampus.shared.test.property;

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
   * Consulting date ID.
   */
  public static final long CONSULTING_DATE_ID = 1L;

  /**
   * Consulting date.
   */
  public static final String CONSULTING_DATE = "Friday 14-16";
}

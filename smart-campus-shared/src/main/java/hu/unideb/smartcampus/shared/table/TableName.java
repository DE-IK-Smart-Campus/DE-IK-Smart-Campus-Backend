package hu.unideb.smartcampus.shared.table;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Table name provider.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TableName {

  /**
   * User table.
   */
  public static final String TABLE_NAME_USER = "user";

  /**
   * Instructor table.
   */
  public static final String TABLE_NAME_INSTRUCTOR = "instructor";

  /**
   * Consulting date table.
   */
  public static final String TABLE_NAME_CONSULTING_DATE = "consulting_date";

  /**
   * User consulting date table.
   */
  public static final String TABLE_NAME_USER_CONSULTING_DATE = "user_consulting_date";

  /**
   * Subject table.
   */
  public static final String TABLE_NAME_SUBJECT = "subject";

  public static final String TABLE_NAME_SUBJECT_DETAILS = "subject_details";
}

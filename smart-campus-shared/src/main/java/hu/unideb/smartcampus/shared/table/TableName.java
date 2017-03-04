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
   * Instructor table.
   */
  public static final String TABLE_NAME_CONSULTING_DATE = "consulting_date";

  /**
   * Subject table.
   */
  public static final String TABLE_NAME_SUBJECT = "subject";
}

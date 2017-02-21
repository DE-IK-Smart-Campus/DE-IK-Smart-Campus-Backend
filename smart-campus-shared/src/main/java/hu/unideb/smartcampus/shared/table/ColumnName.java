package hu.unideb.smartcampus.shared.table;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Column name provider.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ColumnName {

  /**
   * User column name.
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class UserColumnName {

    /**
     * Username column.
     */
    public static final String COLUMN_NAME_USERNAME = "username";

    /**
     * Password column.
     */
    public static final String COLUMN_NAME_PASSWORD = "password";

    /**
     * Role column.
     */
    public static final String COLUMN_NAME_ROLE = "role";
  }
}

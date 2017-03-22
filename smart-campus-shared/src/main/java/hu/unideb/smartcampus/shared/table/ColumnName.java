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

  /**
   * Instructor column.
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class InstructorColumnName {

    /**
     * Name column.
     */
    public static final String COLUMN_NAME_NAME = "name";
    
    /**
     * Room column.
     */
    public static final String COLUMN_NAME_ROOM = "room";

  }

  /**
   * Consulting date column.
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class ConsultingDateColumnName {

    /**
     * Date column.
     */
    public static final String COLUMN_NAME_DATE = "date";

  }
  
  /**
   * Consulting date column.
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class UserConsultingDateColumnName {

    /**
     * Reason column.
     */
    public static final String COLUMN_NAME_REASON = "reason";
    
    /**
     * Duration column.
     */
    public static final String COLUMN_NAME_DURATION = "duration";
  }

  /**
   * Subject details column.
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class SubjectDetailsColumnName {

    /**
     * Subject name column.
     */
    public static final String COLUMN_NAME_SUBJECT_NAME = "subject_name";

    /**
     * Instructors column.
     */
    public static final String COLUMN_NAME_INSTRUCTORS = "instructors";

    /**
     * Subject type column.
     */
    public static final String COLUMN_NAME_SUBJECT_TYPE = "subject_type";
  }

}

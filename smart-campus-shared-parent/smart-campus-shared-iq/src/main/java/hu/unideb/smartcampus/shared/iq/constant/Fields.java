package hu.unideb.smartcampus.shared.iq.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constains field names in the XML tags.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Fields {

  /**
   * Subject IQ request fields.
   */
  public static class SubjectIqRequestFields {
    /**
     * Student field.
     */
    public static final String STUDENT = "student";

    /**
     * Subject field.
     */
    public static final String SUBJECT = "subject";

    /**
     * Instructor field.
     */
    public static final String INSTRUCTOR = "instructor";

    /**
     * Instructor ID field.
     */
    public static final String INSTRUCTORID = "instructorId";

    /**
     * Name field.
     */
    public static final String NAME = "name";
  }

  /**
   * Instructor consulting dates IQ request fields.
   */
  public static class InstructorConsultingDatesIqRequestFields {
    /**
     * Consulting date.
     */
    public static final String CONSULTING_DATE = "consultingDate";

    /**
     * Consulting date.
     */
    public static final String CONSULTING_DATE_ID = "consultingDateId";

    /**
     * From to date field.
     */
    public static final String FROM_TO_DATE = "fromToDate";

    /**
     * From.
     */
    public static final String FROM = "from";

    /**
     * To.
     */
    public static final String TO = "to";

    /**
     * Reserved sum.
     */
    public static final String RESERVED_SUM = "reservedSum";

    /**
     * Instructor ID field.
     */
    public static final String INSTRUCTORID = "instructorId";
  }

  /**
   * Instructor consulting dates IQ request fields.
   */
  public static class SignUpForConsultingDateFields {

    /**
     * User ID.
     */
    public static final String USER_ID = "userId";

    /**
     * Consulting hour ID.
     */
    public static final String CONSULTING_HOUR_ID = "consultingHourId";

    /**
     * Reason.
     */
    public static final String REASON = "reason";

    /**
     * Duration.
     */
    public static final String DURATION = "duration";

  }

}

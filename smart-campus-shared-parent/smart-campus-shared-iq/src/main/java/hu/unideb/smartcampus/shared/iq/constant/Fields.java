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
     * Subjects tag.
     */
    public static final String SUBJECTS = "subjects";

    /**
     * Subject field.
     */
    public static final String SUBJECT = "subject";

    /**
     * Instructor field.
     */
    public static final String INSTRUCTOR = "instructor";

    /**
     * Instructors tag.
     */
    public static final String INSTRUCTORS = "instructors";

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
   * Save subjects by ICS IQ request fields.
   */
  public static class SaveSubjectsIcsIqFields {

    /**
     * Student field.
     */
    public static final String STUDENT = "student";

    /**
     * ICS link field.
     */
    public static final String ICS = "ics";

    /**
     * Status message field.
     */
    public static final String STATUS_MESSAGE = "statusMessage";
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
     * Consulting dates tag.
     */
    public static final String CONSULTING_DATES = "consultingDates";

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

    /**
     * Response message.
     */
    public static final String RESPONSE_MESSAGE = "responseMessage";

  }

  /**
   * Calendar subjects IQ request fields.
   */
  public static class CalendarSubjectIqRequestFields {
    /**
     * Student field.
     */
    public static final String STUDENT = "student";

    /**
     * Subjects tag.
     */
    public static final String SUBJECT_EVENTS = "subjects";

    /**
     * Appointments tag.
     */
    public static final String APPOINTMENTS = "appointments";
    
    /**
     * Appointments field.
     */
    public static final String APPOINTMENT = "appointment";


    /**
     * Subject field.
     */
    public static final String SUBJECT = "subject";

    /**
     * Subject field.
     */
    public static final String SUBJECT_NAME = "subjectName";

    /**
     * When field.
     */
    public static final String WHEN = "when";

    /**
     * Where field.
     */
    public static final String WHERE = "where";

    /**
     * Description field.
     */
    public static final String DESCRIPTION = "description";

    /**
     * From field.
     */
    public static final String FROM = "from";

    /**
     * To field.
     */
    public static final String TO = "to";
  }


}

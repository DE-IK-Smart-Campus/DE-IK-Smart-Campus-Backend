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

    /**
     * Instructor name field.
     */
    public static final String INSTRUCTORNAME = "instructorName";
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
    public static final String STUDENT = "st";

    /**
     * Subjects tag.
     */
    public static final String SUBJECT_EVENTS = "subs";

    /**
     * Appointments tag.
     */
    public static final String APPOINTMENTS = "apps";

    /**
     * Appointments field.
     */
    public static final String APPOINTMENT = "app";


    /**
     * Subject field.
     */
    public static final String SUBJECT = "sub";

    /**
     * Subject field.
     */
    public static final String SUBJECT_NAME = "na";

    /**
     * When field.
     */
    public static final String WHEN = "wn";

    /**
     * Where field.
     */
    public static final String WHERE = "wr";

    /**
     * Description field.
     */
    public static final String DESCRIPTION = "dc";

    /**
     * From field.
     */
    public static final String FROM = "f";

    /**
     * To field.
     */
    public static final String TO = "t";

    /**
     * Start period field.
     */
    public static final String START_PERIOD = "str";

    /**
     * End period field.
     */
    public static final String END_PERIOD = "end";
  }

  /**
   * Custom event IQ request fields.
   */
  public static class CustomEventIqRequestFields {

    /**
     * Student field.
     */
    public static final String STUDENT = "student";

    /**
     * Custom events tag.
     */
    public static final String CUSTOM_EVENTS = "events";

    /**
     * Custom event field.
     */
    public static final String CUSTOM_EVENT = "event";

    /**
     * Custom event id field.
     */
    public static final String EVENT_ID = "eventId";

    /**
     * Event name tag.
     */
    public static final String EVENT_NAME = "eventName";

    /**
     * Event description field.
     */
    public static final String EVENT_DESCRIPTION = "eventDescription";

    /**
     * Event place field.
     */
    public static final String EVENT_PLACE = "eventPlace";

    /**
     * Event when field.
     */
    public static final String EVENT_WHEN = "eventWhen";
    
    /**
     * Event start field.
     */
    public static final String EVENT_START = "eventStart";

    /**
     * Event end field.
     */
    public static final String EVENT_END = "eventEnd";

    /**
     * Event repeat field.
     */
    public static final String EVENT_REPEAT = "eventRepeat";

    /**
     * Reminder field.
     */
    public static final String REMINDER = "reminder";

    /**
     * GUID field.
     */
    public static final String GUID = "guid";
  }


  /**
   * User chat list fields
   */
  public static class UserChatListIqRequestFields {

    /**
     * Student field.
     */
    public static final String STUDENT = "student";

    /**
     * Chats tag.
     */
    public static final String CHATS = "chats";

    /**
     * Chat field.
     */
    public static final String CHAT = "chat";

    /**
     * Rooms tag.
     */
    public static final String ROOMS = "rooms";

    /**
     * Room field.
     */
    public static final String ROOM = "room";
  }

  /**
   * MUC list.
   */
  public static class AddMucChatIqField {

    /**
     * Student field.
     */
    public static final String STUDENT = "student";

    /**
     * Room field.
     */
    public static final String ROOM = "room";
  }

  /**
   * User chat list fields
   */
  public static class AddUserChatIqFields {

    /**
     * Student field.
     */
    public static final String STUDENT = "student";

    /**
     * Chat field.
     */
    public static final String CHAT = "chat";
  }

  /**
   * Generate office hours fields.
   */
  public static class GenerateOfficeHoursIqFields {

    /**
     * Generated hours field.
     */
    public static final String CREATED_HOURS = "createdHours";

    /**
     * Instructor ID field.
     */
    public static final String INSTRUCTOR_ID = "instructorId";

    /**
     * Intervall field.
     */
    public static final String INTERVALL = "intervall";

    /**
     * Office hours tag.
     */
    public static final String OFFICE_HOURS = "officeHours";

    /**
     * Office hour field.
     */
    public static final String OFFICE_HOUR = "officeHour";

    /**
     * From date field.
     */
    public static final String FROM_DATE = "fromDate";

    /**
     * To date field.
     */
    public static final String TO_DATE = "toDate";

    /**
     * Day field.
     */
    public static final String DAY = "day";

    /**
     * From field.
     */
    public static final String FROM = "from";

    /**
     * To field.
     */
    public static final String TO = "to";
  }

  /**
   * Student IQ element fields.
   */
  public static class StudentIqElementFields {

    /**
     * Student name field.
     */
    public static final String STUDENT_NAME = "studentName";

    /**
     * Student name field.
     */
    public static final String NEPTUN_IDENTIFIER = "neptunIdentifier";

    /**
     * Reason field.
     */
    public static final String REASON = "reason";

    /**
     * Duration field.
     */
    public static final String DURATION = "duration";
  }

  /**
   * List instructor consulting dates IQ fields.
   */
  public static class ListInstructorConsultingDatesIqFields {

    /**
     * Instructor ID field.
     */
    public static final String INSTRUCTORID = "instructorId";

    /**
     * Students tag.
     */
    public static final String STUDENTS = "students";

    /**
     * Student field.
     */
    public static final String STUDENT = "student";

    /**
     * One week field.
     */
    public static final String ONE_WEEK = "oneWeek";
  }
}

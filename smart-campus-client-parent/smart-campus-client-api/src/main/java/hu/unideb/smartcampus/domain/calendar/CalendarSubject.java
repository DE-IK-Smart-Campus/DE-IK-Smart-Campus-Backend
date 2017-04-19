package hu.unideb.smartcampus.domain.calendar;

import java.util.Collections;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
public class CalendarSubject {
  /**
   * SubjectsModel name.
   */
  private String subjectName;

  /**
   * Where.
   */
  private String where;

  /**
   * Event description.
   */
  private String description;

  /**
   * Appointment times.
   */
  private List<AppointmentTime> appointmentTimes;

  public CalendarSubject() {
    appointmentTimes = Collections.emptyList();
  }

  /**
   * Constructs a CalendarSubjectIqElement instance.
   */
  @Builder
  public CalendarSubject(String subjectName, String where, String description,
                         List<AppointmentTime> appointmentTimes) {
    this.subjectName = subjectName;
    this.where = where;
    this.description = description;
    this.appointmentTimes = appointmentTimes;
  }
}

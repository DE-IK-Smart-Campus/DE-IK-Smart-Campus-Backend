package hu.unideb.smartcampus.shared.iq.request.element;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Calendar subject IQ element.
 *
 * <p>
 * 
 * <pre>
 * {@code
 * 
 * <when>567</when> <!--in long-->
 * <subjectName>RFT</subjectName>
 * <where>IF 01</where>
 * <desc>John LABORATORY</desc>
 * 
 *  }
 * </pre>
 * 
 * </p>
 */
@Data
@NoArgsConstructor
public class CalendarSubjectIqElement implements Serializable {

  /**
   * UID.
   */
  private static final long serialVersionUID = 6717626218636857093L;

  /**
   * Subject name.
   */
  private String subjectName;

  /**
   * When.
   */
  private Long when;

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
  private List<AppointmentTimeIqElement> appointmentTimes;

  /**
   * Constructs a CalendarSubjectIqElement instance.
   */
  @Builder
  public CalendarSubjectIqElement(String subjectName, Long when, String where, String description,
      List<AppointmentTimeIqElement> appointmentTimes) {
    this.subjectName = subjectName;
    this.when = when;
    this.where = where;
    this.description = description;
    this.appointmentTimes = appointmentTimes;
  }



}

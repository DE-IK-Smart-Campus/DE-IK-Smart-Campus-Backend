package hu.unideb.smartcampus.shared.iq.request.element;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Appointment time.
 * 
 * <p>
 * 
 * <pre>
 * {@code
 * 
 * <id>1</id>
 * <when>456</when> <!--in long-->
 * <from>123</from> <!--in long-->
 * <to>321</to> <!--in long-->
 * <present>true</present>
 * 
 *  }
 * </pre>
 * 
 * </p>
 */
@Data
@NoArgsConstructor
public class AppointmentTimeIqElement {

  /**
   * ID of the appointment.
   */
  private Long id;

  /**
   * From in Long from timestamp.
   */
  private Long from;

  /**
   * To in Long from Timestamp.
   */
  private Long to;

  /**
   * When, Date.
   */
  private Long when;

  /**
   * Is present?
   */
  private boolean present;

  /**
   * Constructs an IQ element.
   * 
   * @param when when.
   * @param from from.
   * @param to to.
   * @param id id of the appointment object.
   * @param present true if the student was there, otherwise false.
   */
  @Builder
  public AppointmentTimeIqElement(
      Long when,
      Long from,
      Long to,
      Long id,
      boolean present) {
    this.from = from;
    this.to = to;
    this.when = when;
    this.id = id;
    this.present = present;
  }
}

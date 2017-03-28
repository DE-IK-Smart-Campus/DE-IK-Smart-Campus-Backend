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
 * <when>456</when> <!--in long-->
 * <from>123</from> <!--in long-->
 * <to>321</to> <!--in long-->
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
   * Constructrs an IQ element.
   */
  @Builder
  public AppointmentTimeIqElement(Long when, Long from, Long to) {
    this.from = from;
    this.to = to;
    this.when = when;
  }


}

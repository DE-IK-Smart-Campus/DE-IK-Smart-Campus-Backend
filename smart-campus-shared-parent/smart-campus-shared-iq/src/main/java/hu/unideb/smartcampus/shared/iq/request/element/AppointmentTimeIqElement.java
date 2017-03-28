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
   * Constructrs an IQ element.
   */
  @Builder
  public AppointmentTimeIqElement(Long from, Long to) {
    this.from = from;
    this.to = to;
  }


}

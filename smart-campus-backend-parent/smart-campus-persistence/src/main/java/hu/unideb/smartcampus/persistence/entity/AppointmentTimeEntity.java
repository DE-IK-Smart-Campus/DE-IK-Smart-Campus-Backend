package hu.unideb.smartcampus.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Appointment time entity.
 */
@Data
@NoArgsConstructor
@Embeddable
public class AppointmentTimeEntity implements Serializable {

  private static final long serialVersionUID = 1418512641975571834L;

  /**
   * Start date time.
   */
  @Column(name = "startDate")
  private LocalDateTime startDateTime;

  /**
   * End date time.
   */
  @Column(name = "endDate")
  private LocalDateTime endDateTime;

  /**
   * Builder.
   */
  @Builder
  public AppointmentTimeEntity(final LocalDateTime startDateTime, final LocalDateTime endDateTime) {
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
  }
}

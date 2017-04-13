package hu.unideb.smartcampus.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Appointment time entity.
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "subject_event_appointment")
@Deprecated
public class AppointmentTimeEntity extends BaseEntity<Long> implements Serializable {

  private static final long serialVersionUID = 1418512641975571834L;

  /**
   * Start date time.
   */
  @Column(name = "start_date")
  private LocalDateTime startDateTime;

  /**
   * End date time.
   */
  @Column(name = "end_date")
  private LocalDateTime endDateTime;

  /**
   * Was present.
   */
  @Column(name = "was_present")
  private Boolean wasPresent;

  /**
   * Builder.
   */
  @Builder
  public AppointmentTimeEntity(final LocalDateTime startDateTime, final LocalDateTime endDateTime,
      final Boolean wasPresent) {
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.wasPresent = wasPresent;
  }
}

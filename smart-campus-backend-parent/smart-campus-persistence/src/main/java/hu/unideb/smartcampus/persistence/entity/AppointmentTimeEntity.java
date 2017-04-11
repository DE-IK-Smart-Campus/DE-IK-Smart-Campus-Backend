package hu.unideb.smartcampus.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
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
public class AppointmentTimeEntity extends BaseEntity<Long> implements Serializable {

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
   * Was present.
   */
  @Column(name = "wasPresent")
  private Boolean wasPresent;

  /**
   * User who has this appointement.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  private UserEntity user;

  /**
   * Builder.
   */
  @Builder
  public AppointmentTimeEntity(final LocalDateTime startDateTime, final LocalDateTime endDateTime,
      final Boolean wasPresent, final UserEntity user) {
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.wasPresent = wasPresent;
    this.user = user;
  }
}

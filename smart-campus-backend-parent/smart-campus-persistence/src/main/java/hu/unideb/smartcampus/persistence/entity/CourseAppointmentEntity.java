package hu.unideb.smartcampus.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import hu.unideb.smartcampus.persistence.listener.CustomEventEntityListener;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Course appointment entity.
 */
@Data
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "course_appointment")
@EntityListeners(CustomEventEntityListener.class)
public class CourseAppointmentEntity extends BaseEntity<Long> {

  /**
   * Start date
   */
  @Column(name = "start_date")
  private LocalDateTime startDate;

  /**
   * Event end.
   */
  @Column(name = "end_date")
  private LocalDateTime endDate;

  /**
   * Was user present?
   */
  @Column(name = "present")
  private Boolean wasPresent;

  /**
   * Constructs a course appointment entity.
   */
  @Builder
  public CourseAppointmentEntity(LocalDateTime startDate, LocalDateTime endDate,
      Boolean wasPresent) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.wasPresent = wasPresent;
  }



}

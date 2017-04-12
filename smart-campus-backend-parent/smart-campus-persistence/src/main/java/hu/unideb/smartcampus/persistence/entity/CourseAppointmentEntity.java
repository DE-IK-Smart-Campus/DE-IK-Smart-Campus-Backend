package hu.unideb.smartcampus.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@EqualsAndHashCode(exclude = {"wasPresent", "id"})
@Table(name = "course_appointment")
public class CourseAppointmentEntity extends BaseEntity<Long> {

  /**
   * Course code.
   */
  @Column(name = "course_code")
  private String courseCode;

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
  public CourseAppointmentEntity(Long id, LocalDateTime startDate, LocalDateTime endDate,
      Boolean wasPresent, String courseCode) {
    super(id);
    this.startDate = startDate;
    this.endDate = endDate;
    this.wasPresent = wasPresent;
    this.courseCode = courseCode;
  }
}

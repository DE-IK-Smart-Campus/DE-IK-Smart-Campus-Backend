package hu.unideb.smartcampus.service.api.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Course appointment.
 */
@Data
@ToString(callSuper = true)
public class CourseAppointment extends BaseObject<Long> {


  /**
   * Course code.
   */
  private final String courseCode;

  /**
   * Start date
   */
  private final LocalDateTime startDate;

  /**
   * Event end.
   */
  private final LocalDateTime endDate;

  /**
   * Was user present?
   */
  private final Boolean wasPresent;

  /**
   * Constructs a course appointment entity.
   */
  @Builder
  public CourseAppointment(Long id, LocalDateTime startDate, LocalDateTime endDate,
      Boolean wasPresent, String courseCode) {
    super(id);
    this.startDate = startDate;
    this.endDate = endDate;
    this.wasPresent = wasPresent;
    this.courseCode = courseCode;
  }
}

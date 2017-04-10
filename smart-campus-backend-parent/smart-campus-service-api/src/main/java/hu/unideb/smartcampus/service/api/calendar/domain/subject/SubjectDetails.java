package hu.unideb.smartcampus.service.api.calendar.domain.subject;

import java.time.LocalDate;
import java.util.List;

import hu.unideb.smartcampus.service.api.domain.InstructorWrapper;
import lombok.Builder;
import lombok.Data;

/**
 * SubjectDetails.
 */
@Data
@Builder
public class SubjectDetails {

  /**
   * Subject name.
   */
  private final String subjectName;

  /**
   * Course code.
   */
  private String courseCode;

  /**
   * Subject type.
   */
  private final SubjectType subjectType;

  /**
   * Teacher names.
   */
  private List<InstructorWrapper> instructors;

  /**
   * Start date time.
   */
  private LocalDate startPeriod;

  /**
   * End date time.
   */
  private LocalDate endPeriod;

}

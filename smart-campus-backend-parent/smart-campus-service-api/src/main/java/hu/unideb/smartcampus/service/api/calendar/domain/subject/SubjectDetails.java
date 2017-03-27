package hu.unideb.smartcampus.service.api.calendar.domain.subject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
   * Subject type.
   */
  private final SubjectType subjectType;

  /**
   * Teacher names.
   */
  private List<String> teacherNames;

  /**
   * Start date time.
   */
  private LocalDate startPeriod;

  /**
   * End date time.
   */
  private LocalDate endPeriod;

}

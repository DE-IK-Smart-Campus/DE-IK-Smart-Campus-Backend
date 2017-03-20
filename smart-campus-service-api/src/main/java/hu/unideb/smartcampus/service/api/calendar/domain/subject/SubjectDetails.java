package hu.unideb.smartcampus.service.api.calendar.domain.subject;

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
}

package hu.unideb.smartcampus.service.api.calendar.domain.subject;

import lombok.Builder;
import lombok.Data;

/**
 * SubjectDetails.
 */
@Data
@Builder
public class SubjectDetails {

  private final String subjectName;

  private final SubjectType subjectType;
}

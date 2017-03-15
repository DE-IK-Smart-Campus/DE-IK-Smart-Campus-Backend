package hu.unideb.smartcampus.service.api.calendar.domain.subject;

import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * SubjectDetails.
 */
@Data
@Builder
public class SubjectDetails {

  private final String subjectName;

  private final String roomLocation;

  private final List<String> teacherNames;

  private final SubjectType subjectType;
}

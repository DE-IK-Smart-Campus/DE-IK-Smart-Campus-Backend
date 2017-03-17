package hu.unideb.smartcampus.service.api.calendar.domain.subject;

import java.util.List;
import hu.unideb.smartcampus.service.api.domain.Instructor;
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

  private List<Instructor> instructors;
}

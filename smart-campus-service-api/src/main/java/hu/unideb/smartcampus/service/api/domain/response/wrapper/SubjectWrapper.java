package hu.unideb.smartcampus.service.api.domain.response.wrapper;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Wrapper for result of Retrieve Subjects.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SubjectWrapper extends BaseWrapper {

  /**
   * Subject name.
   */
  private String subjectName;

  /**
   * Teachers.
   */
  private List<InstructorWrapper> instructors;

  /**
   * Constructs SubjectWrapper.
   */
  @Builder
  public SubjectWrapper(final String subjectName, final List<InstructorWrapper> instructors) {
    this.subjectName = subjectName;
    this.instructors = instructors;
  }
}

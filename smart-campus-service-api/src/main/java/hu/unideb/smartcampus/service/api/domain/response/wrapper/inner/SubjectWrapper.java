package hu.unideb.smartcampus.service.api.domain.response.wrapper.inner;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * Wrapper for result of Retrieve Subjects.
 *
 */
@Data
public class SubjectWrapper {

  /**
   * Subject name.
   */
  private final String name;

  /**
   * Teachers.
   */
  private final List<InstructorWrapper> instructors;

  /**
   * Constructs SubjectWrapper.
   */
  @Builder
  public SubjectWrapper(final String name, final List<InstructorWrapper> instructors) {
    this.name = name;
    this.instructors = instructors;
  }
}

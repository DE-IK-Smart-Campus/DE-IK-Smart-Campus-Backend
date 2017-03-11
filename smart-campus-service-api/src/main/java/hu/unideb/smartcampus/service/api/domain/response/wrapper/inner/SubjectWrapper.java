package hu.unideb.smartcampus.service.api.domain.response.wrapper.inner;

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
public class SubjectWrapper {

  /**
   * Subject name.
   */
  private String name;

  /**
   * Teachers.
   */
  private List<InstructorWrapper> instructors;

  /**
   * Constructs SubjectWrapper.
   */
  @Builder
  public SubjectWrapper(final String name, final List<InstructorWrapper> instructors) {
    this.name = name;
    this.instructors = instructors;
  }
}

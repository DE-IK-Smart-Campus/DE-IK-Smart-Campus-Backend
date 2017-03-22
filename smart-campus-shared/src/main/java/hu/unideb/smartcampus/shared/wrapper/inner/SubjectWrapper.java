package hu.unideb.smartcampus.shared.wrapper.inner;

import java.util.ArrayList;
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
   * SubjectEvent name.
   */
  private final String name;

  /**
   * Teachers.
   */
  private final List<InstructorWrapper> instructors;

  /**
   * Todo.
   */
  public SubjectWrapper() {
    this.name = "";
    this.instructors = new ArrayList<>();
  }

  /**
   * Constructs SubjectWrapper.
   */
  @Builder
  public SubjectWrapper(final String name, final List<InstructorWrapper> instructors) {
    this.name = name;
    this.instructors = instructors;
  }
}

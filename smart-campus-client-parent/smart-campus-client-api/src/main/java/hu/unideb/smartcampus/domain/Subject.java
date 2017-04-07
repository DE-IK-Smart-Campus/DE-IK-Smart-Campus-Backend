package hu.unideb.smartcampus.domain;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Subject.
 */
@Data
@ToString(callSuper = true)
public class Subject {

  /**
   * Name of the subject.
   */
  private final String name;

  /**
   * Instructors of the subject.
   */
  private final List<Instructor> instructors;

  /**
   * Constructs subject entity.
   */
  @Builder
  public Subject(String name, List<Instructor> instructors) {
    this.name = name;
    this.instructors = instructors;
  }
}

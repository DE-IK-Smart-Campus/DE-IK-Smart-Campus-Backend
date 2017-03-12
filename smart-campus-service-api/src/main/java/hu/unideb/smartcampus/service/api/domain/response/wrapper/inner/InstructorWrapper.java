package hu.unideb.smartcampus.service.api.domain.response.wrapper.inner;

import lombok.Builder;
import lombok.Data;

/**
 * Instructor wrapper.
 *
 */
@Data
public class InstructorWrapper {

  /**
   * Instructor's id.
   */
  private final Long instructorId;

  /**
   * Instructor's name.
   */
  private final String name;

  /**
   * Constructs an InstructorWrapper instance.
   */
  @Builder
  public InstructorWrapper(final Long instructorId, final String name) {
    this.instructorId = instructorId;
    this.name = name;
  }



}

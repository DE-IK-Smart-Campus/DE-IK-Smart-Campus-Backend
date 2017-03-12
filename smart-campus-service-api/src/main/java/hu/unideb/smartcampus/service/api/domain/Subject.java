package hu.unideb.smartcampus.service.api.domain;

import lombok.Builder;
import lombok.Data;

/**
 * Subject.
 *
 */
@Data
public class Subject extends BaseObject<Long> {

  /**
   * Name of the instructor.
   */
  private final String name;

  /**
   * Constructs a subject entity.
   */
  @Builder
  public Subject(final Long id, final String name) {
    super(id);
    this.name = name;
  }

}

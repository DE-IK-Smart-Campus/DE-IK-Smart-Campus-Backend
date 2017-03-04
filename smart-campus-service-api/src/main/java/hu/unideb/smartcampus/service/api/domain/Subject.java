package hu.unideb.smartcampus.service.api.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Subject.
 *
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class Subject {

  /**
   * Name of the instructor.
   */
  private String name;

  /**
   * Constructs a subject entity.
   */
  @Builder
  public Subject(final String name) {
    this.name = name;
  }

}

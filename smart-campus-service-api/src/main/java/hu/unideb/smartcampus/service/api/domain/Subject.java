package hu.unideb.smartcampus.service.api.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Subject.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class Subject extends BaseObject<Long> {

  /**
   * Name of the instructor.
   */
  private String name;

  /**
   * Constructs a subject entity.
   */
  @Builder
  public Subject(final Long id, final String name) {
    super(id);
    this.name = name;
  }

}

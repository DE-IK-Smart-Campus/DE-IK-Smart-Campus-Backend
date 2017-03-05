package hu.unideb.smartcampus.service.api.domain;

import java.util.Set;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Instructor.
 *
 */
@Data
@ToString(callSuper = true)
public class Instructor extends BaseObject<Long> {

  /**
   * Name of the instructor.
   */
  private String name;

  /**
   * Instructor's consulting hours.
   */
  private Set<ConsultingDate> consultingDates;

  /**
   * Subjects of the instructor.
   */
  private Set<Subject> subjects;

  /**
   * Constructs instructor entity.
   */
  @Builder
  public Instructor(final Long id, final String name, final Set<ConsultingDate> consultingDates,
      final Set<Subject> subjects) {
    super(id);
    this.name = name;
    this.consultingDates = consultingDates;
    this.subjects = subjects;
  }

}

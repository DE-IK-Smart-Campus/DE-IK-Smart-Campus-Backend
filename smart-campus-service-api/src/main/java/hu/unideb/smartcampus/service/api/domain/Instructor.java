package hu.unideb.smartcampus.service.api.domain;

import java.util.Set;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Instructor.
 *
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class Instructor {

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
  public Instructor(final String name, final Set<ConsultingDate> consultingDates,
      final Set<Subject> subjects) {
    this.name = name;
    this.consultingDates = consultingDates;
    this.subjects = subjects;
  }

}

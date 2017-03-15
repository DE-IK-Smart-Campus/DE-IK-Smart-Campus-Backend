package hu.unideb.smartcampus.service.api.domain;

import java.util.Set;

import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
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
  private final String name;

  /**
   * Instructor's consulting hours.
   */
  private final Set<ConsultingDate> consultingDates;

  /**
   * Subjects of the instructor.
   */
  private final Set<SubjectDetails> subjects;

  /**
   * Constructs instructor entity.
   */
  @Builder
  public Instructor(final Long id, final String name, final Set<ConsultingDate> consultingDates,
      final Set<SubjectDetails> subjects) {
    super(id);
    this.name = name;
    this.consultingDates = consultingDates;
    this.subjects = subjects;
  }

}

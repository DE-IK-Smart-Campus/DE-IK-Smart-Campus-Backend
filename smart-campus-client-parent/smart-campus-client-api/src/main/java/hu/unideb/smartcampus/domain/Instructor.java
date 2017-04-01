package hu.unideb.smartcampus.domain;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Instructor.
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
  private final List<ConsultingDate> consultingDates;

  /**
   * Constructs instructor entity.
   */
  @Builder
  public Instructor(final Long id, final String name, final List<ConsultingDate> consultingDates) {
    super(id);
    this.name = name;
    this.consultingDates = consultingDates;
  }
}

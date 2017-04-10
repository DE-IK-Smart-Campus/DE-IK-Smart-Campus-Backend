package hu.unideb.smartcampus.service.api.domain;

import java.util.Set;

import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Instructor.
 */
@Data
@ToString(callSuper = true)
public class InstructorWrapper extends BaseObject<Long> {

  /**
   * Name of the instructor.
   */
  private final String name;

  /**
   * Neptun identifier.
   */
  private final String neptunIdentifier;

  /**
   * Room.
   */
  private final String room;

  /**
   * Constructs instructor entity.
   */
  @Builder
  public InstructorWrapper(final Long id, final String name, final String neptunIdentifier,
      final String room) {
    super(id);
    this.name = name;
    this.room = room;
    this.neptunIdentifier = neptunIdentifier;
  }

}

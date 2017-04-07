package hu.unideb.smartcampus.shared.iq.request.element;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Instrcutor representing class.
 *
 */
@Data
@NoArgsConstructor
public class InstructorIqElement implements Serializable {

  /**
   * UID.
   */
  private static final long serialVersionUID = 3024586908379544351L;

  /**
   * Instructor's id.
   */
  private Long instructorId;

  /**
   * Instructor's name.
   */
  private String name;

  /**
   * Constructs an InstructorWrapper instance.
   */
  @Builder
  public InstructorIqElement(Long instructorId, String name) {
    this.instructorId = instructorId;
    this.name = name;
  }
}

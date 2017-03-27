package hu.unideb.smartcampus.shared.iq.request.element;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Subject class.
 */
@Data
@NoArgsConstructor
public class SubjectIqElement implements Serializable {

  /**
   * UID.
   */
  private static final long serialVersionUID = 6717626218636857093L;

  /**
   * Subject name.
   */
  private String subjectName;

  /**
   * Subject name.
   */
  private List<InstructorIqElement> instructors;

  /**
   * Constructs a SubjectWrapper instance.
   */
  @Builder
  public SubjectIqElement(String subjectName, List<InstructorIqElement> instructors) {
    this.subjectName = subjectName;
    this.instructors = instructors;
  }



}

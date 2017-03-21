package hu.unideb.smartcampus.shared.iq.request.element;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Subject class.
 */
@Data
@NoArgsConstructor
@XmlRootElement(name = "subject")
@XmlAccessorType(XmlAccessType.NONE)
public class SubjectIqElement implements Serializable {

  /**
   * UID.
   */
  private static final long serialVersionUID = 6717626218636857093L;

  /**
   * Subject name.
   */
  @XmlElement(name = "name")
  private String subjectName;

  /**
   * Subject name.
   */
  @XmlElementWrapper
  @XmlElement(name = "instructor")
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

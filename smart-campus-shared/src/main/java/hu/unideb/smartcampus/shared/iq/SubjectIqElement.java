package hu.unideb.smartcampus.shared.iq;

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
public class SubjectIqElement {

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

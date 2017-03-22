package hu.unideb.smartcampus.shared.iq.request.element;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Instrcutor representing class.
 *
 */
@Data
@NoArgsConstructor
@XmlRootElement(name = "instructor")
@XmlAccessorType(XmlAccessType.NONE)
public class InstructorIqElement implements Serializable {

  /**
   * UID.
   */
  private static final long serialVersionUID = 3024586908379544351L;

  /**
   * Instructor's id.
   */
  @XmlElement(name = "instructorId")
  private Long instructorId;

  /**
   * Instructor's name.
   */
  @XmlElement(name = "name")
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

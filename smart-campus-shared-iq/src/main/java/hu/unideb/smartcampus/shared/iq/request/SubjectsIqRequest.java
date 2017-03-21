package hu.unideb.smartcampus.shared.iq.request;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import hu.unideb.smartcampus.shared.iq.request.element.SubjectIqElement;
import lombok.Data;

/**
 * Subject Iq.
 */
@Data
@XmlRootElement(name = SubjectsIqRequest.ELEMENT, namespace = AbstractSmartCampusIq.BASE_NAMESPACE)
@XmlAccessorType(XmlAccessType.NONE)
public class SubjectsIqRequest extends AbstractSmartCampusIq {

  /**
   * Element.
   */
  public static final String ELEMENT = "askSubjects";

  /**
   * User.
   */
  @XmlElement
  private String student;

  /**
   * Things to good to have.
   */
  @XmlElementWrapper
  @XmlElement(name = "subject")
  private List<SubjectIqElement> subjects;

  /**
   * Def contrcutros.
   */
  public SubjectsIqRequest() {
    super(ELEMENT);
    subjects = new ArrayList<>();
  }

  /**
   * Constructs.
   */
  public SubjectsIqRequest(String student, List<SubjectIqElement> subjects) {
    super(ELEMENT);
    this.student = student;
    this.subjects = subjects;
  }

  @Override
  protected String getElement() {
    return ELEMENT;
  }

  @Override
  protected Object getInstance() {
    return this;
  }

  @Override
  protected Class<? extends AbstractSmartCampusIq> getIqClass() {
    return this.getClass();
  }
}

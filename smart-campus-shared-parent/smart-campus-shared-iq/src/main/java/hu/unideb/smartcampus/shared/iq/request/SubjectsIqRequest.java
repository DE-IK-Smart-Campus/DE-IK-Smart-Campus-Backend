package hu.unideb.smartcampus.shared.iq.request;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import hu.unideb.smartcampus.shared.iq.request.element.SubjectIqElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Subject Iq.
 */
@Getter
@Setter
@EqualsAndHashCode
@XmlRootElement(name = SubjectsIqRequest.ELEMENT, namespace = BaseSmartCampusIq.BASE_NAMESPACE)
@XmlAccessorType(XmlAccessType.NONE)
public class SubjectsIqRequest extends BaseSmartCampusIq {

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
  protected BaseSmartCampusIq getInstance() {
    return this;
  }

  @Override
  protected Class<? extends BaseSmartCampusIq> getIqClass() {
    return this.getClass();
  }

  public String toXml() {
    return "SubjectsIqRequest [student=" + student + ", subjects=" + subjects + "]";
  }
  
  
}

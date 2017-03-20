package hu.unideb.smartcampus.shared.iq;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jivesoftware.smack.packet.ExtensionElement;

/**
 * Subject Iq.
 * <p>
 * 
 * <pre>
 * {@code
 * 
 * <askSubjects xmlns='http://inf.unideb.hu/smartcampus/student'>
 *  <student>Nándor Holozsnyák</student>
 *      <subjects>
 *          <name>AI</name>
 *      </subjects>
 *      <subjects>
 *          <name>IE</name>
 *      </subjects>
 * </askSubjects>
 * 
 *  }
 * </pre>
 * </p>
 * 
 */
@XmlRootElement(name = SubjectsIq.ELEMENT, namespace = SubjectsIq.NAMESPACE)
@XmlAccessorType(XmlAccessType.NONE)
public class SubjectsIq extends AbstractSmartCampusIq {

  /**
   * Subject class.
   */
  @XmlRootElement(name = "subject")
  @XmlAccessorType(XmlAccessType.FIELD)
  public static class Subject {

    /**
     * Thing name.
     */
    @XmlElement(name = "name")
    private String subjectName;

    public Subject() {
      // needed.
    }

    /**
     * Constructs a Subject instance.
     */
    public Subject(String thingName) {
      this.subjectName = thingName;
    }

  }

  /**
   * Element.
   */
  public static final String ELEMENT = "askSubjects";

  /**
   * Namespace.
   */
  public static final String NAMESPACE = "http://inf.unideb.hu/smartcampus/student";

  /**
   * User.
   */
  @XmlElement
  private String student;

  /**
   * Things to good to have.
   */
  @XmlElement(name = "subjects")
  private List<Subject> subjects;

  /**
   * Def contrcutros.
   */
  public SubjectsIq() {
    super(ELEMENT, NAMESPACE);
    subjects = new ArrayList<>();
  }

  /**
   * Constructs.
   */
  public SubjectsIq(String user, List<Subject> things) {
    super(ELEMENT, NAMESPACE);
    this.student = user;
    this.subjects = things;
  }

  public String getStudent() {
    return student;
  }

  public List<Subject> getSubjects() {
    return subjects;
  }

  public void setStudent(String student) {
    this.student = student;
  }


  public void setSubjects(List<Subject> subjects) {
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

  @Override
  protected String getNamespace() {
    return NAMESPACE;
  }

  @Override
  protected ExtensionElement getExtension() {
    return new SubjectExtension();
  }
}

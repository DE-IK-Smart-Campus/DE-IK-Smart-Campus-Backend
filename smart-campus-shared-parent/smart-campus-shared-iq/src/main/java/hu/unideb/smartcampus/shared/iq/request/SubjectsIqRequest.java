package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.SubjectIqRequestFields.INSTRUCTOR;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SubjectIqRequestFields.INSTRUCTORID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SubjectIqRequestFields.INSTRUCTORS;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SubjectIqRequestFields.NAME;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SubjectIqRequestFields.STUDENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SubjectIqRequestFields.SUBJECT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SubjectIqRequestFields.SUBJECTS;

import java.util.ArrayList;
import java.util.List;

import hu.unideb.smartcampus.shared.iq.request.element.InstructorIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.SubjectIqElement;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Subject IQ request.
 */
@Getter
@Setter
public class SubjectsIqRequest extends BaseSmartCampusIq {

  /**
   * Element.
   */
  public static final String ELEMENT = "askSubjects";

  /**
   * Student's username.
   */
  private String student;

  /**
   * Student's subjects.
   */
  private List<SubjectIqElement> subjects;

  /**
   * Default constructor.
   */
  public SubjectsIqRequest() {
    super(ELEMENT);
    subjects = new ArrayList<>();
  }

  /**
   * Constructs.
   */
  @Builder
  public SubjectsIqRequest(String student, List<SubjectIqElement> subjects) {
    super(ELEMENT);
    this.student = student;
    this.subjects = subjects;
  }

  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    builder.append(tag(STUDENT, student));
    buildSubjects(builder);
  }

  private void buildSubjects(StringBuilder builder) {
    if (subjects != null || !subjects.isEmpty()) {
      builder.append(openTag(SUBJECTS));
      for (SubjectIqElement subjectIqElement : subjects) {
        builder.append(openTag(SUBJECT));
        builder.append(tag(NAME, subjectIqElement.getSubjectName()));
        builder.append(openTag(INSTRUCTORS));
        buildInstructors(builder, subjectIqElement);
        builder.append(closeTag(INSTRUCTORS));
        builder.append(closeTag(SUBJECT));
      }
      builder.append(closeTag(SUBJECTS));
    }
  }

  private void buildInstructors(StringBuilder builder, SubjectIqElement subjectIqElement) {
    for (InstructorIqElement instructor : subjectIqElement.getInstructors()) {
      builder.append(openTag(INSTRUCTOR));
      builder.append(tag(INSTRUCTORID, instructor.getInstructorId()));
      builder.append(tag(NAME, instructor.getName()));
      builder.append(closeTag(INSTRUCTOR));
    }
  }

  @Override
  public IQChildElementXmlStringBuilder getIQChildElementBuilder(
      IQChildElementXmlStringBuilder xml) {
    xml.append(" >");
    xml.append(toXml());
    return xml;
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }
}

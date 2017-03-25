package hu.unideb.smartcampus.shared.iq.request;

import java.util.ArrayList;
import java.util.List;

import hu.unideb.smartcampus.shared.iq.request.element.InstructorIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.SubjectIqElement;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Subject Iq.
 */
@Getter
@Setter
public class SubjectsIqRequest extends BaseSmartCampusIq {

  /**
   * Element.
   */
  public static final String ELEMENT = "askSubjects";

  /**
   * User.
   */
  private String student;

  /**
   * Things to good to have.
   */
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
  @Builder
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
  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    builder.append(">");
    builder.append("<student>").append(student).append("</student>");
    buildSubjects(builder);
  }

  private void buildSubjects(StringBuilder builder) {
    if (subjects != null || !subjects.isEmpty()) {
      builder.append("<subjects>");
      for (SubjectIqElement subjectIqElement : subjects) {
        builder.append("<subject>");
        builder.append("<name>").append(subjectIqElement.getSubjectName()).append("</name>");
        builder.append("<instructors>");
        buildInstructors(builder, subjectIqElement);
        builder.append("</instructors>");
        builder.append("</subject>");
      }
      builder.append("</subjects>");
    }
  }

  private void buildInstructors(StringBuilder builder, SubjectIqElement subjectIqElement) {
    for (InstructorIqElement instructor : subjectIqElement.getInstructors()) {
      builder.append("<instructor>");
      builder.append("<instructorId>").append(instructor.getInstructorId())
          .append("</instructorId>");
      builder.append("<name>").append(instructor.getName()).append("</name>");
      builder.append("</instructor>");
    }
  }


}

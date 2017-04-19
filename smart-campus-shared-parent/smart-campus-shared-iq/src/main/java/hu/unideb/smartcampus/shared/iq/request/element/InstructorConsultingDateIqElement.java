package hu.unideb.smartcampus.shared.iq.request.element;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.CONSULTING_DATE_ID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.DAY;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.STUDENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.STUDENTS;
import static hu.unideb.smartcampus.shared.iq.util.IqProviderUtil.closeTag;
import static hu.unideb.smartcampus.shared.iq.util.IqProviderUtil.openTag;
import static hu.unideb.smartcampus.shared.iq.util.IqProviderUtil.tag;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Instructor consulting hour wrapper.
 */
@Data
@NoArgsConstructor
public class InstructorConsultingDateIqElement implements Serializable {

  /**
   * UID.
   */
  private static final long serialVersionUID = -2873216923468627648L;

  /**
   * Consulting date id.
   */
  private Long consultingDateId;

  /**
   * Student's subjects.
   */
  private List<StudentIqElement> students;

  /**
   * Day of the consulting date.
   */
  private String day;

  /**
   * Builder.
   */
  @Builder
  public InstructorConsultingDateIqElement(Long consultingDateId, List<StudentIqElement> students,
      String day) {
    this.consultingDateId = consultingDateId;
    this.students = students;
    this.day = day;
  }

  /**
   * Generates XML.
   */
  public String toXml() {
    StringBuilder builder = new StringBuilder();
    builder.append(tag(CONSULTING_DATE_ID, consultingDateId));
    builder.append(tag(DAY, day));
    buildStudents(builder);
    return builder.toString();
  }

  private void buildStudents(StringBuilder builder) {
    if (students != null || !students.isEmpty()) {
      builder.append(openTag(STUDENTS));
      for (StudentIqElement student : students) {
        builder.append(openTag(STUDENT));
        builder.append(student.toXml());
        builder.append(closeTag(STUDENT));
      }
      builder.append(closeTag(STUDENTS));
    }
  }

}

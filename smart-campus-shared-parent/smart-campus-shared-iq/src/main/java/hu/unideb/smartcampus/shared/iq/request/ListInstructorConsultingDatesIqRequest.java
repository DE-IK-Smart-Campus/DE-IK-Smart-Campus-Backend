package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.INSTRUCTORID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.ONE_WEEK;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.STUDENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.STUDENTS;

import java.util.ArrayList;
import java.util.List;

import hu.unideb.smartcampus.shared.iq.request.element.StudentIqElement;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * List instructor consulting dates.
 */
@Getter
@Setter
public class ListInstructorConsultingDatesIqRequest extends BaseSmartCampusIqRequest {

  /**
   * Element.
   */
  public static final String ELEMENT = "listInstructorConsultingDates";

  /**
   * Instructor id.
   */
  private Long instructorId;

  /**
   * Student's subjects.
   */
  private List<StudentIqElement> students;

  /**
   * Set true if you want to list only the upcoming 7 days consulting dates.
   */
  private boolean oneWeek;

  /**
   * Default constructor.
   */
  public ListInstructorConsultingDatesIqRequest() {
    super(ELEMENT);
    students = new ArrayList<>();
  }

  /**
   * Constructs.
   */
  @Builder
  public ListInstructorConsultingDatesIqRequest(Long instructorId,
      List<StudentIqElement> students, boolean oneWeek) {
    super(ELEMENT);
    this.oneWeek = oneWeek;
    this.students = students;
    this.instructorId = instructorId;
  }

  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    builder.append(tag(INSTRUCTORID, instructorId));
    builder.append(tag(ONE_WEEK, oneWeek));
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    buildStudents(builder);
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

  @Override
  public String getElement() {
    return ELEMENT;
  }
}

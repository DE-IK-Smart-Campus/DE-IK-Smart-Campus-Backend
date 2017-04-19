package hu.unideb.smartcampus.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class InstructorConsultingDate {

  /**
   * Consulting date id.
   */
  private Long consultingDateId;

  /**
   * Student's subjects.
   */
  private List<Student> students;

  /**
   * Day of the consulting date.
   */
  private String day;

  /**
   * Builder.
   */
  @Builder
  public InstructorConsultingDate(Long consultingDateId, List<Student> students,
      String day) {
    this.consultingDateId = consultingDateId;
    this.students = students;
    this.day = day;
  }
}

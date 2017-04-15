package hu.unideb.smartcampus.service.api.domain.response.wrapper;

import java.util.List;

import hu.unideb.smartcampus.webservice.api.neptun.StudentCourse;
import lombok.Builder;
import lombok.Data;

/**
 * Course info wrapper.
 */
@Data
public class CourseInfoWrapper {

  /**
   * All courses.
   */
  private List<StudentCourse> courses;

  /**
   * Last semester courses.
   */
  private List<StudentCourse> lastSemesterCourses;

  /**
   * Filtered last semester courses.
   */
  private List<StudentCourse> filteredLastSemesterCourses;

  /**
   * Constructor.
   */
  @Builder
  public CourseInfoWrapper(List<StudentCourse> courses,
      List<StudentCourse> lastSemesterCourses,
      List<StudentCourse> filteredLastSemesterCourses) {
    this.courses = courses;
    this.lastSemesterCourses = lastSemesterCourses;
    this.filteredLastSemesterCourses = filteredLastSemesterCourses;
  }



}

package hu.unideb.smartcampus.service.api.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.service.api.domain.InstructorWrapper;
import hu.unideb.smartcampus.webservice.api.neptun.StudentCourse;

/**
 * Teacher util for generating teachers.
 */
@Component
public class TeacherUtil {

  /**
   * Get teachers from courses by course code.
   * 
   * @param courses courses list.
   * @param course course name.
   * @return teachers in list.
   */
  public List<InstructorWrapper> getTeachersByCourseCode(List<StudentCourse> courses,
      StudentCourse course) {
    if (course.getCourseCode() == null)
      return new ArrayList<>();

    List<StudentCourse> collect = filterCoursesByCourseCode(courses, course);

    List<InstructorWrapper> result =
        collect.stream()
            .map(this::createTeacher)
            .collect(Collectors.toList());
    
    return result.stream()
        .distinct()
        .collect(Collectors.toList());
  }

  private List<StudentCourse> filterCoursesByCourseCode(List<StudentCourse> courses,
      StudentCourse course) {
    return courses.stream()
        .filter(p -> course.getSemester().equals(p.getSemester()))
        .filter(p -> course.getCourseCode().equals(p.getCourseCode()))
        .collect(Collectors.toList());
  }

  private InstructorWrapper createTeacher(StudentCourse studentCourse) {
    return InstructorWrapper.builder()
        .name(studentCourse.getLecturerDisplayName())
        .neptunIdentifier(studentCourse.getLecturerNeptunIdentifier())
        .build();
  }

}

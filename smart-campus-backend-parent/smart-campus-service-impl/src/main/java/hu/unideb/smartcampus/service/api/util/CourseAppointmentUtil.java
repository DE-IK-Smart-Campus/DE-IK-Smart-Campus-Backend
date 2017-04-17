package hu.unideb.smartcampus.service.api.util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.service.api.domain.CourseAppointment;
import hu.unideb.smartcampus.webservice.api.neptun.StudentCourse;

/**
 * Course appointment util.
 */
@Component
public class CourseAppointmentUtil {

  /**
   * Get appointments by courses.
   * 
   * @param courses courses.
   * @param courseCode course code.
   * @param subjectEvent subject event.
   * @return appointement list.
   */
  public List<CourseAppointment> getAppointmentListByCourseCode(List<StudentCourse> courses,
      String courseCode, SubjectEvent subjectEvent) {
    List<StudentCourse> filteredCourseList =
        filterCoursesByCourseCode(courses, courseCode);
    return filteredCourseList.stream()
        .filter(this::isRealCourse)
        .map(studentCourse -> buildCourseAppointement(studentCourse, subjectEvent))
        .collect(Collectors.toList());
  }

  private boolean isRealCourse(StudentCourse studentCourse) {
    return studentCourse.getStartTime() != null && studentCourse.getEndTime() != null;
  }

  private List<StudentCourse> filterCoursesByCourseCode(List<StudentCourse> courses,
      String courseCode) {
    return courses.stream()
        .filter(p -> p.getCourseCode().equals(courseCode))
        .collect(Collectors.toList());
  }

  private CourseAppointment buildCourseAppointement(StudentCourse studentCourse,
      SubjectEvent subjectEvent) {
    return CourseAppointment.builder()
        .courseCode(studentCourse.getCourseCode())
        .startDate(parseDate(studentCourse.getStartTime()))
        .endDate(parseDate(studentCourse.getEndTime()))
        .subjectEvent(subjectEvent)
        .wasPresent(false)
        .build();
  }

  private LocalDateTime parseDate(String date) {
    if (date == null)
      return null;
    return LocalDateTime.parse(date.split("\\+")[0]);
  }

}

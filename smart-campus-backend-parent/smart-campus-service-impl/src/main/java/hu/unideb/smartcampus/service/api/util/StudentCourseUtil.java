package hu.unideb.smartcampus.service.api.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.service.api.domain.response.wrapper.CourseInfoWrapper;
import hu.unideb.smartcampus.webservice.api.neptun.StudentCourse;
import hu.unideb.smartcampus.webservice.api.neptun.StudentTimeTable;

/**
 * Student course util.
 */
@Component
public class StudentCourseUtil {

  /**
   * Get course information wrapper objec by student time table.
   * 
   * @param studentTimeTable
   * @return
   */
  public CourseInfoWrapper getCourseInfosByStudentTimeTable(StudentTimeTable studentTimeTable) {

    List<StudentCourse> courses = studentTimeTable.getCourses();
    Map<String, List<StudentCourse>> bySemester = courses
        .stream()
        .collect(Collectors.groupingBy(StudentCourse::getSemester));

    List<StudentCourse> lastSemesterCourses =
        new ArrayList<>(bySemester.get(findLastSemesterKey(bySemester.keySet())));
    List<StudentCourse> filteredLastSemesterCourses = new ArrayList<>(
        getFilteredCourseList(bySemester.get(findLastSemesterKey(bySemester.keySet()))));

    return CourseInfoWrapper
        .builder()
        .courses(courses)
        .lastSemesterCourses(lastSemesterCourses)
        .filteredLastSemesterCourses(filteredLastSemesterCourses)
        .build();
  }

  private String findLastSemesterKey(Set<String> keys) {
    return keys.stream()
        .sorted(Comparator.reverseOrder())
        .findFirst()
        .get();
  }

  private List<StudentCourse> getFilteredCourseList(List<StudentCourse> courses) {
    return courses.stream()
        .filter(distinctByKey(course -> course.getCourseCode()))
        .collect(Collectors.toList());
  }

  private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }

}

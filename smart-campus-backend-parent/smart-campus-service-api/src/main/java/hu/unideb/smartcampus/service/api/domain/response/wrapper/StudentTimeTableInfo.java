package hu.unideb.smartcampus.service.api.domain.response.wrapper;

import java.util.List;

import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.service.api.domain.CourseAppointment;
import lombok.Builder;
import lombok.Data;

/**
 * Student time table info.
 */
@Data
public class StudentTimeTableInfo {

  private final List<SubjectEvent> subjectEvents;

  private final List<CourseAppointment> courseAppointments;

  /**
   * Constructs an instance.
   */
  @Builder
  public StudentTimeTableInfo(List<SubjectEvent> subjectEvents,
      List<CourseAppointment> courseAppointments) {
    this.subjectEvents = subjectEvents;
    this.courseAppointments = courseAppointments;
  }



}

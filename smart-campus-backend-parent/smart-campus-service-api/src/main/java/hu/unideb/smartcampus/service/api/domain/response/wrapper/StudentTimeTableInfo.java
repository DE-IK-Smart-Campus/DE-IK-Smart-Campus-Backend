package hu.unideb.smartcampus.service.api.domain.response.wrapper;

import java.util.List;

import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import lombok.Builder;
import lombok.Data;

/**
 * Student time table info.
 */
@Data
public class StudentTimeTableInfo {

  private final List<SubjectDetails> subjectDetails;

  private final List<SubjectEvent> subjectEvents;


  /**
   * Constructs an instance.
   */
  @Builder
  public StudentTimeTableInfo(final List<SubjectDetails> subjectDetails,
      final List<SubjectEvent> subjectEvents) {
    this.subjectEvents = subjectEvents;
    this.subjectDetails = subjectDetails;
  }
}
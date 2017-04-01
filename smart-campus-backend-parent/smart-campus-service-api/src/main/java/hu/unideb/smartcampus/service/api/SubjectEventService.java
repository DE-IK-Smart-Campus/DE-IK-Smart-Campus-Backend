package hu.unideb.smartcampus.service.api;

import java.time.LocalDate;
import java.util.List;

import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;

/**
 * Subject event service.
 */
public interface SubjectEventService {

  /**
   * Get all subject event by user id.
   */
  List<SubjectEvent> getAllSubjectEventByUserId(Long userId);

  /**
   * Get all subject event by subject details.
   */
  List<SubjectEvent> getAllSubjectEventBySubjectDetails(List<SubjectDetails> subjectDetailsList);

  /**
   * Save.
   */
  void save(SubjectEvent subjectEvent);

  /**
   * Save.
   */
  void save(List<SubjectEvent> subjectEvents);

  /**
   * Get all subject event by user name.
   */
  List<SubjectEvent> getAllSubjectEventByUsername(String username);

  /**
   * Get subjects within range by user name.
   */
  List<SubjectEvent> getSubjectEventWithinRangeByUsername(String username, LocalDate from,
      LocalDate to);
}

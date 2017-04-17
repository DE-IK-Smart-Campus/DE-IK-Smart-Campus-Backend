package hu.unideb.smartcampus.service.api;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.service.api.domain.CourseAppointment;
import hu.unideb.smartcampus.webservice.api.neptun.StudentTimeTable;

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
   * 
   * @return
   */
  SubjectEvent save(SubjectEvent subjectEvent);

  /**
   * Save if not exists in db.
   * 
   * @return
   */
  SubjectEvent saveIfNotExists(SubjectEvent subjectEvent);

  /**
   * Save.
   * 
   * @return
   */
  List<SubjectEvent> save(List<SubjectEvent> subjectEvents);

  /**
   * Get all subject event by user name.
   */
  List<SubjectEvent> getAllSubjectEventByUsername(String username);

  /**
   * Get subjects within range by user name.
   */
  List<SubjectEvent> getSubjectEventWithinRangeByUsername(String username, LocalDate from,
      LocalDate to);

  /**
   * Save subjects to student.
   */
  List<SubjectEvent> saveSubjectEvents(StudentTimeTable studentTimeTable, final String userName)
      throws IOException;

  /**
   * Get course appointment list by username and subject event.
   * 
   * @param username student's username.
   * @param subjectEvent subject event.
   * @return list of user course appointments.
   */
  List<CourseAppointment> getCourseAppointmentByUsernameAndSubjectEvent(String username,
      SubjectEvent subjectEvent);
}

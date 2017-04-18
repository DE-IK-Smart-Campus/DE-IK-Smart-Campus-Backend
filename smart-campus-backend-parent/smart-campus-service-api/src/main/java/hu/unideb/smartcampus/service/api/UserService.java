package hu.unideb.smartcampus.service.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.service.api.domain.CourseAppointment;
import hu.unideb.smartcampus.service.api.domain.User;

/**
 * UserService.
 */
public interface UserService {

  /**
   * Get by id.
   */
  Optional<User> getById(Long id);

  /**
   * Retrieves {@link User} in {@link Optional}.
   */
  Optional<User> getByUsername(String username);

  /**
   * Saves the user.
   * 
   * @param user to be saved
   * @return the persisted entity with its id set
   */
  User save(User user);

  /**
   * Get id by username.
   */
  Long getIdByUsername(String username);

  /**
   * Get user actual subjects in range.
   */
  Set<SubjectDetails> getSubjectsWithinRangeByUsername(String username, LocalDate from,
      LocalDate to);

  List<CourseAppointment> getCourseAppointmentsByUsernameAndSubjectEvent(String username,
      SubjectEvent subjectEvent);

  List<CourseAppointment> getCourseAppointmensWithinRange(String username, LocalDate from,
      LocalDate to);

  /**
   * Get course appointments by username.
   * 
   * @param username user's username.
   * @return user's course appointments.
   */
  List<CourseAppointment> getCourseAppointmentsByUsername(String username);
  
  /**
   * Get user subject events.
   */
  List<SubjectEvent> getSubjectEventsByUsername(String username);
}

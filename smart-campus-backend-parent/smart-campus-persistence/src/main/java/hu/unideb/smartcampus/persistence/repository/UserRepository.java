package hu.unideb.smartcampus.persistence.repository;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.unideb.smartcampus.persistence.entity.CourseAppointmentEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectEventEntity;
import hu.unideb.smartcampus.persistence.entity.UserEntity;

/**
 * User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  /**
   * Find user by username.
   */
  UserEntity findByUsername(String username);

  /**
   * Get actual subjects by username.
   */
  Set<SubjectDetailsEntity> getSubjectsByUsername(String username);

  /**
   * Get user id by username.
   */
  Long getIdByUsername(String username);

  /**
   * Get actual subjects by username.
   */
  Set<SubjectDetailsEntity> getSubjectsWithinRangeByUsername(String username, LocalDate from,
      LocalDate to);

  /**
   * Get single chat list by username.
   */
  Set<String> getSingleChatListByUsername(String username);

  /**
   * Get MUC chat list by username.
   */
  Set<String> getMucChatListByUsername(String username);

  /**
   * Get actual course appointements between range.
   */
  Set<CourseAppointmentEntity> getCourseAppointmensWithinRange(String username, LocalDate from,
      LocalDate to);

  /**
   * Get actual course appointements between range.
   */
  Set<CourseAppointmentEntity> getCourseAppointmentsByUsername(String username);

  /**
   * Get actual course appointements between range.
   */
  Set<CourseAppointmentEntity> getCourseAppointmentsBySubjectEvent(String username,
      SubjectEventEntity subjectEvent);

  /**
   * Get user subject events.
   */
  Set<SubjectEventEntity> getSubjectEventsByUsername(String username);
}



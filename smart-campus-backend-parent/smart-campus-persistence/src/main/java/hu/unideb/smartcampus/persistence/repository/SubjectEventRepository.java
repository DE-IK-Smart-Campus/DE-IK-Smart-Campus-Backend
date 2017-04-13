package hu.unideb.smartcampus.persistence.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectEventEntity;

/**
 * Subject event repository.
 */
public interface SubjectEventRepository extends JpaRepository<SubjectEventEntity, Long> {

  /**
   * Find all by subject details.
   */
  List<SubjectEventEntity> findAllBySubjectDetailsEntityIn(Set<SubjectDetailsEntity> subjectDetailsEntity);
  
  /**
   * Find subject event by subject details entity.
   */
  List<SubjectEventEntity> findBySubjectDetailsEntity(SubjectDetailsEntity subjectDetailsEntity);
  
  /**
   * Find subject event by subject details entity.
   */
  List<SubjectEventEntity> findBySubjectDetailsEntityAndRoomLocation(SubjectDetailsEntity subjectDetailsEntity, String roomLocation);
  
  /**
   * Find subject event by subject details entity.
   */
  List<SubjectEventEntity> findBySubjectDetailsEntityAndRoomLocationAndCourseCode(SubjectDetailsEntity subjectDetailsEntity, String roomLocation,String courseCode);
}

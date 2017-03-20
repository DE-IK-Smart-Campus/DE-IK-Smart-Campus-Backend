package hu.unideb.smartcampus.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;
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
}

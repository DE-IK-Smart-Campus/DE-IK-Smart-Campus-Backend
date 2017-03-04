package hu.unideb.smartcampus.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.unideb.smartcampus.persistence.entity.SubjectEntity;

/**
 * User repository.
 */
@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

  /**
   * Find user by name.
   */
  SubjectEntity findByName(String username);
}

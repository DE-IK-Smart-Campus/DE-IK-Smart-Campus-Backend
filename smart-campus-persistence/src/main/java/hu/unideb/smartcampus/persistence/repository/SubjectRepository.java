package hu.unideb.smartcampus.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.persistence.entity.SubjectEntity;

/**
 * Subject repository.
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

  /**
   * Find user by name.
   */
  SubjectEntity findByName(String username);
}

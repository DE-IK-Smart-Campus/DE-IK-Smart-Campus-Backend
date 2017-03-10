package hu.unideb.smartcampus.persistence.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.persistence.entity.SubjectEntity;
import hu.unideb.smartcampus.persistence.entity.UserEntity;

/**
 * User repository.
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  /**
   * Find user by username.
   */
  UserEntity findByUsername(String username);

  /**
   * Get actual subjects by username.
   */
  Set<SubjectEntity> getSubjectsByUsername(String username);

  /**
   * Get actual subjects by id.
   */
  Set<SubjectEntity> getSubjectsByUserId(Long id);
}

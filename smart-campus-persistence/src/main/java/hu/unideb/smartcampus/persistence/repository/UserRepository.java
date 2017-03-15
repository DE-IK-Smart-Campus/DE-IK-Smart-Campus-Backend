package hu.unideb.smartcampus.persistence.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
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
   * Get actual subjects by id.
   */
  Set<SubjectDetailsEntity> getSubjectsByUserId(Long id);
}

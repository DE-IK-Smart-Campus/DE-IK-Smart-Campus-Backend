package hu.unideb.smartcampus.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.unideb.smartcampus.persistence.entity.CustomEventEntity;

/**
 * User repository.
 */
@Repository
public interface CustomEventRepository extends JpaRepository<CustomEventEntity, Long> {

  /**
   * Get custom events by username.
   */
  List<CustomEventEntity> getEventsByUsername(String username);

  /**
   * Get custom events by user id.
   */
  List<CustomEventEntity> getEventsByUserId(Long userId);
  
  /**
   * Delete custom event by GUID.
   */
  Integer deleteByGuid(String guid);

}

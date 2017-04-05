package hu.unideb.smartcampus.persistence.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.unideb.smartcampus.persistence.entity.UserConsultingDateEntity;

/**
 * User consulting date repository.
 */
@Repository
public interface UserConsultingDateRepository
    extends JpaRepository<UserConsultingDateEntity, Long> {

  /**
   * Get user consulting date entites by consulting date IDs.
   */
  List<UserConsultingDateEntity> getUserConsultingDatesByConsultingDateId(List<Long> ids);

  /**
   * Get user consulting date entites by instructor id.
   */
  List<UserConsultingDateEntity> getUserConsultingDatesByInstructorId(Long instructorId);
  
  /**
   * Get user consulting date entites by instructor id.
   */
  List<UserConsultingDateEntity> getUserConsultingDatesByInstructorIdBetweenRange(Long instructorId, Timestamp from, Timestamp to);

}

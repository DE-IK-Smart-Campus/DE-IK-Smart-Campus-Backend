package hu.unideb.smartcampus.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.persistence.entity.UserConsultingDateEntity;

/**
 * User consulting date repository.
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface UserConsultingDateRepository
    extends JpaRepository<UserConsultingDateEntity, Long> {

}

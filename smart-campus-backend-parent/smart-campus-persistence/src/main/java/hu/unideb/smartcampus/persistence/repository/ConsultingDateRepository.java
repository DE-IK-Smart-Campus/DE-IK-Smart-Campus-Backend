package hu.unideb.smartcampus.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;

/**
 * Consulting date repository.
 */
@Repository
public interface ConsultingDateRepository extends JpaRepository<ConsultingDateEntity, Long> {

}

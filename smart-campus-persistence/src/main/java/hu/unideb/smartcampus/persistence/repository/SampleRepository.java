package hu.unideb.smartcampus.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.unideb.smartcampus.persistence.entity.SampleEntity;

/**
 * Sample repository which provides operations for {@link SampleEntity}.
 */
@Repository
public interface SampleRepository extends JpaRepository<SampleEntity, Long> {

}

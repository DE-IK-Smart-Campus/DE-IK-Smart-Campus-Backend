package hu.unideb.smartcampus.persistence.repository;

import hu.unideb.smartcampus.persistence.entity.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends JpaRepository<Long, SampleEntity> {
}

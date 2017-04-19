package hu.unideb.smartcampus.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.unideb.smartcampus.persistence.entity.CourseAppointmentEntity;

/**
 * Course appointment repository.
 */
@Repository
public interface CourseAppointmentRepository extends JpaRepository<CourseAppointmentEntity, Long> {


}

package hu.unideb.smartcampus.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.shared.primarykey.SubjectDetailsPrimaryKey;

/**
 * Subject details repository.
 */
public interface SubjectDetailsRepository extends JpaRepository<SubjectDetailsEntity, SubjectDetailsPrimaryKey> {

}

package hu.unideb.smartcampus.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.persistence.entity.primarykey.SubjectDetailsPrimaryKey;


public interface SubjectDetailsRepository extends JpaRepository<SubjectDetailsEntity, SubjectDetailsPrimaryKey> {

}

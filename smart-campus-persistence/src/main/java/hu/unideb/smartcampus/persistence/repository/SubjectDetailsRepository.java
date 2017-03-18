package hu.unideb.smartcampus.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;


public interface SubjectDetailsRepository extends JpaRepository<SubjectDetailsEntity, Long> {

  SubjectDetailsEntity findBySubjectDetailsPrimaryKeySubjectName(String subjectName);
}

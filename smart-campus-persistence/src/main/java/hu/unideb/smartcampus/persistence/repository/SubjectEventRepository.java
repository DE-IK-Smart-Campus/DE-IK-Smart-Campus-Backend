package hu.unideb.smartcampus.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;
import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectEventEntity;

public interface SubjectEventRepository extends JpaRepository<SubjectEventEntity, Long> {

  List<SubjectEventEntity> findAllBySubjectDetailsEntityIn(Set<SubjectDetailsEntity> subjectDetailsEntity);
}

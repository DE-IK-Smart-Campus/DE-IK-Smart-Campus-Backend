package hu.unideb.smartcampus.service.api.converter.toentity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;

@Component
public class SubjectDetailsToSubjectDetailsEntityConverter implements Converter<SubjectDetails, SubjectDetailsEntity> {

  @Override
  public SubjectDetailsEntity convert(final SubjectDetails subjectDetails) {
    return subjectDetails == null ? null : convertSubjectDetailsToSubjectDetailsEntity(subjectDetails);
  }

  private SubjectDetailsEntity convertSubjectDetailsToSubjectDetailsEntity(final SubjectDetails subjectDetails) {
    return SubjectDetailsEntity.builder()
        .subjectName(subjectDetails.getSubjectName())
        .subjectType(subjectDetails.getSubjectType().name())
        .build();
  }
}

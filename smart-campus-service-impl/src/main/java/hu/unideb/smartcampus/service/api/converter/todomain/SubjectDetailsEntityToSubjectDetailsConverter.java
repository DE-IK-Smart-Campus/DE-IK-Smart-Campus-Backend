package hu.unideb.smartcampus.service.api.converter.todomain;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectType;

@Component
public class SubjectDetailsEntityToSubjectDetailsConverter implements Converter<SubjectDetailsEntity, SubjectDetails> {

  @Override
  public SubjectDetails convert(final SubjectDetailsEntity entity) {
    return entity == null ? null : SubjectDetails.builder()
        .subjectName(entity.getSubjectName())
        .subjectType(SubjectType.valueOf(entity.getSubjectType()))
        .build();
  }
}

package hu.unideb.smartcampus.service.api.converter.todomain;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;

/**
 * Entity to domain.
 *
 */
@Component
public class SubjectEntityToSubjectConverter implements Converter<SubjectDetailsEntity, SubjectDetails> {

  /**
   * {@inheritDoc}.
   */
  @Override
  public SubjectDetails convert(SubjectDetailsEntity entity) {
    return entity == null ? null : SubjectDetails.builder().subjectName(entity.getSubjectName()).build();
  }

}

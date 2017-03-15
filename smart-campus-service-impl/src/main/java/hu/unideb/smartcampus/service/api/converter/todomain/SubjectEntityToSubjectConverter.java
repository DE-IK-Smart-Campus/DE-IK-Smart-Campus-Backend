package hu.unideb.smartcampus.service.api.converter.todomain;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.SubjectEntity;
import hu.unideb.smartcampus.service.api.domain.Subject;

/**
 * Entity to domain.
 *
 */
@Component
public class SubjectEntityToSubjectConverter implements Converter<SubjectEntity, Subject> {

  /**
   * {@inheritDoc}.
   */
  @Override
  public Subject convert(SubjectEntity entity) {
    return entity == null ? null : Subject.builder().name(entity.getName()).build();
  }

}

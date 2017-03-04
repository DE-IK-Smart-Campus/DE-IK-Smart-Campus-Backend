package hu.unideb.smartcampus.service.api.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.persistence.entity.SubjectEntity;
import hu.unideb.smartcampus.persistence.repository.UserRepository;
import hu.unideb.smartcampus.service.api.ConsultingHourService;
import hu.unideb.smartcampus.service.api.converter.todomain.SubjectEntityToSubjectConverter;
import hu.unideb.smartcampus.service.api.domain.Subject;

/**
 * Consulting hours service.
 *
 */
@Service
public class ConsultingHourServiceImpl implements ConsultingHourService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private SubjectEntityToSubjectConverter converter;

  /**
   * {@inheritDoc}.
   */
  @Override
  public Set<Subject> getSubjectsByUserId(Long id) {
    Set<SubjectEntity> subjects = userRepository.getSubjectsByUserId(id);
    return subjects.stream().map(e -> converter.convert(e)).collect(Collectors.toSet());
  }

}

package hu.unideb.smartcampus.service.api.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  /**
   * User repository.
   */
  private final UserRepository userRepository;

  /**
   * Entity to domain converter.
   */
  private final SubjectEntityToSubjectConverter converter;

  /**
   * Constructs an instance of ConsultingHourServiceImpl.
   */
  @Autowired
  public ConsultingHourServiceImpl(UserRepository userRepository,
      SubjectEntityToSubjectConverter converter) {
    this.userRepository = userRepository;
    this.converter = converter;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  @Transactional(readOnly = true)
  public Set<Subject> getSubjectsByUserId(Long id) {
    Set<SubjectEntity> subjects = userRepository.getSubjectsByUserId(id);
    return subjects.stream().map(converter::convert).collect(Collectors.toSet());
  }

}

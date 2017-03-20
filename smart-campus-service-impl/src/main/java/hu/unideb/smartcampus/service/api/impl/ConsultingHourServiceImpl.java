package hu.unideb.smartcampus.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.persistence.repository.UserRepository;
import hu.unideb.smartcampus.service.api.ConsultingHourService;
import hu.unideb.smartcampus.service.api.converter.todomain.SubjectDetailsEntityToSubjectDetailsConverter;

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
  private final SubjectDetailsEntityToSubjectDetailsConverter converter;

  /**
   * Constructs an instance of ConsultingHourServiceImpl.
   */
  @Autowired
  public ConsultingHourServiceImpl(UserRepository userRepository,
      SubjectDetailsEntityToSubjectDetailsConverter converter) {
    this.userRepository = userRepository;
    this.converter = converter;
  }
}

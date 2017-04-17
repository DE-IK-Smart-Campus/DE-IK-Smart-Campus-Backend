package hu.unideb.smartcampus.service.api.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import hu.unideb.smartcampus.persistence.entity.CourseAppointmentEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectEventEntity;
import hu.unideb.smartcampus.persistence.entity.UserEntity;
import hu.unideb.smartcampus.persistence.repository.UserRepository;
import hu.unideb.smartcampus.service.api.UserService;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.service.api.domain.CourseAppointment;
import hu.unideb.smartcampus.service.api.domain.User;

/**
 * Implementation for {@link UserService}.
 */
@Service
public class UserServiceImpl implements UserService {

  /**
   * UserRepository.
   */
  private final UserRepository userRepository;
  /**
   * ConversionService.
   */
  private final ConversionService conversionService;

  /**
   * UserServiceImpl constructor.
   */
  @Autowired
  public UserServiceImpl(final UserRepository userRepository,
      final ConversionService conversionService) {
    this.userRepository = userRepository;
    this.conversionService = conversionService;
  }

  @Override
  public Optional<User> getById(final Long id) {
    Assert.notNull(id);

    return Optional.ofNullable(conversionService.convert(userRepository.findOne(id), User.class));
  }

  /**
   * {@inheritDoc}.
   */
  @Transactional(readOnly = true)
  @Override
  public Optional<User> getByUsername(final String username) {
    Assert.notNull(username);

    return Optional.ofNullable(
        this.conversionService.convert(this.userRepository.findByUsername(username), User.class));
  }

  @Override
  @Transactional
  public User save(final User user) {
    UserEntity userEntity = conversionService.convert(user, UserEntity.class);
    return conversionService.convert(userRepository.save(userEntity), User.class);
  }

  @Transactional(readOnly = true)
  @Override
  public Long getIdByUsername(String username) {
    return userRepository.getIdByUsername(username);
  }

  @Transactional(readOnly = true)
  @Override
  public Set<SubjectDetails> getSubjectsWithinRangeByUsername(String username, LocalDate from,
      LocalDate to) {
    Set<SubjectDetailsEntity> subjects =
        userRepository.getSubjectsWithinRangeByUsername(username, from, to);
    return subjects.stream()
        .map(subjectDetails -> conversionService.convert(subjectDetails, SubjectDetails.class))
        .collect(Collectors.toSet());
  }

  @Transactional(readOnly = true)
  @Override
  public List<CourseAppointment> getCourseAppointmentsByUsernameAndSubjectEvent(String username,
      SubjectEvent subjectEvent) {
    SubjectEventEntity subjectEventEntity =
        conversionService.convert(subjectEvent, SubjectEventEntity.class);
    Set<CourseAppointmentEntity> courseAppointments =
        userRepository.getCourseAppointmentsBySubjectEvent(username, subjectEventEntity);
    return courseAppointments.stream()
        .map(this::toCourseAppointment)
        .collect(Collectors.toList());
  }

  private CourseAppointment toCourseAppointment(CourseAppointmentEntity entity) {
    return conversionService.convert(entity, CourseAppointment.class);
  }
}

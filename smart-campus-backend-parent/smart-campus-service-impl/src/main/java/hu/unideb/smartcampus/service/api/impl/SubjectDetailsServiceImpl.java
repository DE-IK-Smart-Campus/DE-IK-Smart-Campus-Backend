package hu.unideb.smartcampus.service.api.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.persistence.repository.SubjectDetailsRepository;
import hu.unideb.smartcampus.service.api.SubjectDetailsService;
import hu.unideb.smartcampus.service.api.UserService;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.domain.User;
import hu.unideb.smartcampus.shared.exception.UserNotFoundException;
import hu.unideb.smartcampus.shared.primarykey.SubjectDetailsPrimaryKey;


@Service
public class SubjectDetailsServiceImpl implements SubjectDetailsService {

  private final SubjectDetailsRepository subjectDetailsRepository;

  private final UserService userService;

  private final ConversionService conversionService;

  @Autowired
  public SubjectDetailsServiceImpl(final SubjectDetailsRepository subjectDetailsRepository,
      final UserService userService,
      final ConversionService conversionService) {
    this.subjectDetailsRepository = subjectDetailsRepository;
    this.userService = userService;
    this.conversionService = conversionService;
  }

  @Transactional(readOnly = true)
  @Override
  public List<SubjectDetails> getAllSubjectDetailsByUserId(final Long userId) {
    final User user = userService.getById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

    return user.getSubjectEventList()
        .stream()
        .map(event -> event.getSubjectDetails())
        .collect(Collectors.toList());
  }

  @Transactional
  @Override
  public SubjectDetails save(final SubjectDetails subjectDetails) {
    Assert.notNull(subjectDetails);
    SubjectDetailsEntity entity = subjectDetailsRepository
        .save(conversionService.convert(subjectDetails, SubjectDetailsEntity.class));
    return conversionService.convert(entity, SubjectDetails.class);
  }

  @Transactional
  @Override
  public List<SubjectDetails> save(final List<SubjectDetails> subjectDetailsList) {
    Assert.notNull(subjectDetailsList);
    return subjectDetailsList.stream()
        .map(this::saveIfNotExists)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  @Override
  public List<SubjectDetails> getAllSubjectDetailsByUsername(String username) {
    Long userId = userService.getIdByUsername(username);
    return getAllSubjectDetailsByUserId(userId);
  }

  @Transactional(readOnly = true)
  @Override
  public List<SubjectDetails> getSubjectDetailsWithinRangeByUsername(LocalDate from, LocalDate to,
      String username) {
    return userService.getSubjectsWithinRangeByUsername(username, from, to).stream()
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  @Override
  public SubjectDetails getByKey(SubjectDetailsPrimaryKey key) {
    SubjectDetailsEntity findOne = subjectDetailsRepository.findOne(key);
    return conversionService.convert(findOne, SubjectDetails.class);
  }

  private SubjectDetails saveIfNotExists(final SubjectDetails subjectDetails) {
    Assert.notNull(subjectDetails);
    SubjectDetails byKey = getByKey(createKeyBySubjectDetails(subjectDetails));
    SubjectDetailsEntity entity;
    if (byKey == null) {
      entity = subjectDetailsRepository
          .save(conversionService.convert(subjectDetails, SubjectDetailsEntity.class));
    } else {
      entity = conversionService.convert(byKey, SubjectDetailsEntity.class);
    }
    return conversionService.convert(entity, SubjectDetails.class);
  }

  private SubjectDetailsPrimaryKey createKeyBySubjectDetails(SubjectDetails subjectDetails) {
    SubjectDetailsPrimaryKey key = new SubjectDetailsPrimaryKey();
    key.setSubjectName(subjectDetails.getSubjectName());
    key.setSubjectType(subjectDetails.getSubjectType().toString());
    key.setStartPeriod(subjectDetails.getStartPeriod());
    key.setEndPeriod(subjectDetails.getEndPeriod());
    return key;
  }
}

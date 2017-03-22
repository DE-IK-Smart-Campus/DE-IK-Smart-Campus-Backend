package hu.unideb.smartcampus.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.persistence.repository.SubjectDetailsRepository;
import hu.unideb.smartcampus.service.api.SubjectDetailsService;
import hu.unideb.smartcampus.service.api.UserService;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.domain.User;
import hu.unideb.smartcampus.service.api.exception.UserNotFoundException;


@Service
public class SubjectDetailsServiceImpl implements SubjectDetailsService {

  private final SubjectDetailsRepository subjectDetailsRepository;

  private final UserService userService;

  private final ConversionService conversionService;

  @Autowired
  public SubjectDetailsServiceImpl(final SubjectDetailsRepository subjectDetailsRepository, final UserService userService,
                                   final ConversionService conversionService) {
    this.subjectDetailsRepository = subjectDetailsRepository;
    this.userService = userService;
    this.conversionService = conversionService;
  }

  @Transactional
  @Override
  public List<SubjectDetails> getAllSubjectDetailsByUserId(final Long userId) {
    final User user = userService.getById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

    return user.getSubjectDetailsList();
  }

  @Transactional
  @Override
  public void save(final SubjectDetails subjectDetails) {
    Assert.notNull(subjectDetails);

    subjectDetailsRepository.save(conversionService.convert(subjectDetails, SubjectDetailsEntity.class));
  }

  @Transactional
  @Override
  public void save(final List<SubjectDetails> subjectDetailsList) {
    Assert.notNull(subjectDetailsList);

    subjectDetailsList.forEach(subjectDetails -> save(subjectDetails));
    subjectDetailsRepository.flush();
  }
}

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
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;


@Service
public class SubjectDetailsServiceImpl implements SubjectDetailsService {

  private final SubjectDetailsRepository subjectDetailsRepository;

  private final ConversionService conversionService;

  @Autowired
  public SubjectDetailsServiceImpl(final SubjectDetailsRepository subjectDetailsRepository, final ConversionService conversionService) {
    this.subjectDetailsRepository = subjectDetailsRepository;
    this.conversionService = conversionService;
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

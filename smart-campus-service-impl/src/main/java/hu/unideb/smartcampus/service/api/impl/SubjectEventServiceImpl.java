package hu.unideb.smartcampus.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;
import hu.unideb.smartcampus.persistence.entity.SubjectEventEntity;
import hu.unideb.smartcampus.persistence.repository.SubjectEventRepository;
import hu.unideb.smartcampus.service.api.SubjectDetailsService;
import hu.unideb.smartcampus.service.api.SubjectEventService;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;

@Service
public class SubjectEventServiceImpl implements SubjectEventService {

  private final SubjectEventRepository subjectEventRepository;

  private final SubjectDetailsService subjectDetailsService;

  private final ConversionService conversionService;

  @Autowired
  public SubjectEventServiceImpl(final SubjectEventRepository subjectEventRepository, final SubjectDetailsService subjectDetailsService,
                                 final ConversionService conversionService) {
    this.subjectEventRepository = subjectEventRepository;
    this.subjectDetailsService = subjectDetailsService;
    this.conversionService = conversionService;
  }

  @Transactional
  @Override
  public void save(final SubjectEvent subjectEvent) {
    Assert.notNull(subjectEvent);

    subjectEventRepository.save(conversionService.convert(subjectEvent, SubjectEventEntity.class));
  }

  @Transactional
  @Override
  public void save(final List<SubjectEvent> subjectEvents) {
    Assert.notNull(subjectEvents);

    preSavingSubjectDetails(mapSubjectEventListToSubjectDetailsList(subjectEvents));

    subjectEvents.forEach(subjectEvent -> save(subjectEvent));
    subjectEventRepository.flush();
  }

  private void preSavingSubjectDetails(final List<SubjectDetails> subjectDetailsList) {
    subjectDetailsService.save(subjectDetailsList);
  }

  private List<SubjectDetails> mapSubjectEventListToSubjectDetailsList(final List<SubjectEvent> subjectEvents) {
    return subjectEvents.parallelStream()
        .map(subjectEvent -> subjectEvent.getSubjectDetails())
        .collect(Collectors.toList());
  }
}

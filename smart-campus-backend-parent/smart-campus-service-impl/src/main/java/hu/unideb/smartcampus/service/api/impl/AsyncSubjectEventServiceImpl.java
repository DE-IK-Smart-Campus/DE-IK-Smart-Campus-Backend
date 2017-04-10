package hu.unideb.smartcampus.service.api.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.service.api.AsyncSubjectEventService;
import hu.unideb.smartcampus.service.api.SubjectEventService;

/**
 * Service for delegate subject saving with Async.
 * 
 * @author Nandi
 *
 */
@Service
public class AsyncSubjectEventServiceImpl implements AsyncSubjectEventService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AsyncSubjectEventServiceImpl.class);

  private SubjectEventService subjectEventService;

  /**
   */
  @Autowired
  public AsyncSubjectEventServiceImpl(SubjectEventService subjectEventService) {
    this.subjectEventService = subjectEventService;
  }

  /**
   * {@inheritDoc}.
   */
  @Transactional
  @Override
  @Async
  public void saveSubjectEventsAsync(final String neptunIdentifier, final String userName)
      throws IOException {
    LOGGER.debug("Saving user subjects within an async call.");
    subjectEventService.saveSubjectEvents(neptunIdentifier, userName);
  }

}

package hu.unideb.smartcampus.service.api.requestprocess;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.service.api.ConsultingHourService;
import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.service.api.domain.Subject;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.BaseWrapper;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.SubjectResponseWrapper;
import hu.unideb.smartcampus.shared.requestmessages.BaseRequestType;
import hu.unideb.smartcampus.shared.requestmessages.RetrieveSubjectsRequest;

/**
 * Service for retrivie the given user's subjects.
 *
 */
@Component
public class RetrieveSubjectsRequestServiceImpl implements MessageProcessingClass {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(RetrieveSubjectsRequestServiceImpl.class);

  @Autowired
  private ConsultingHourService service;

  /**
   * {@inheritDoc}.
   */
  @Override
  public <T extends BaseWrapper> T getResponse(Object object) {
    LOGGER.info("Retrieving user's subjects.");
    RetrieveSubjectsRequest msg = (RetrieveSubjectsRequest) object;
    Set<Subject> subjects = service.getSubjectsByUserId(msg.getUserId());
    return (T) SubjectResponseWrapper.builder().subjectSet(subjects).build();
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Class<? extends BaseRequestType> getSupportedClass() {
    return RetrieveSubjectsRequest.class;
  }



}

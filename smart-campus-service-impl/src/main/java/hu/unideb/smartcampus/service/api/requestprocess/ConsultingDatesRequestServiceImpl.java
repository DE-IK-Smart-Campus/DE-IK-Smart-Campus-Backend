package hu.unideb.smartcampus.service.api.requestprocess;

import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.BaseWrapper;
import hu.unideb.smartcampus.shared.requestmessages.BaseRequestType;
import hu.unideb.smartcampus.shared.requestmessages.ConsultingDatesRequest;

/**
 * Service for retrivie...
 *
 */
@Component
public class ConsultingDatesRequestServiceImpl implements MessageProcessingClass {

  /**
   * {@inheritDoc}.
   */
  @Override
  public <T extends BaseWrapper> T getResponse(Object object) {
    return null;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Class<? extends BaseRequestType> getSupportedClass() {
    return ConsultingDatesRequest.class;
  }



}

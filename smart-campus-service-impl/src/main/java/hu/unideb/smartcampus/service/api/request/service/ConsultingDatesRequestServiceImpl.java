package hu.unideb.smartcampus.service.api.request.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.BaseWrapper;
import hu.unideb.smartcampus.shared.requestmessages.BaseRequestType;
import hu.unideb.smartcampus.shared.requestmessages.ConsultingDatesRequest;

/**
 * Service for retrivie...
 *
 */
@Service("consultingDatesRequestServiceImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class ConsultingDatesRequestServiceImpl implements MessageProcessingClass<BaseWrapper> {

  public static final String BEAN_NAME = "consultingDatesRequestServiceImpl";

  /**
   * {@inheritDoc}.
   */
  @Override
  public BaseWrapper getResponse(Object object) {
    return null;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Class<? extends BaseRequestType> getSupportedClass() {
    return ConsultingDatesRequest.class;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public String getBeanName() {
    return BEAN_NAME;
  }



}

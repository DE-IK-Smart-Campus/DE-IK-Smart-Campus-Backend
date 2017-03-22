package hu.unideb.smartcampus.service.api.request.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.shared.requestmessages.BaseRequestType;
import hu.unideb.smartcampus.shared.requestmessages.ExampleRequest;
import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;
import hu.unideb.smartcampus.shared.wrapper.ExampleResponseWrapper;

/**
 * Example request processing service implementation.
 *
 */
@Service(ExampleRequestServiceImpl.BEAN_NAME)
@Transactional(propagation = Propagation.REQUIRED)
public class ExampleRequestServiceImpl implements MessageProcessingClass<ExampleResponseWrapper> {

  public static final String BEAN_NAME = "exampleRequestServiceImpl";

  /**
   * {@inheritDoc}.
   */
  @Override
  public ExampleResponseWrapper getResponse(Object object) {
    ExampleRequest request = (ExampleRequest) object;
    return ExampleResponseWrapper.builder().messageType(RequestMessagesConstants.EXAMPLE_RESPONSE)
        .example(request.getExample()).build();
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Class<? extends BaseRequestType> getSupportedClass() {
    return ExampleRequest.class;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public String getBeanName() {
    return BEAN_NAME;
  }

}

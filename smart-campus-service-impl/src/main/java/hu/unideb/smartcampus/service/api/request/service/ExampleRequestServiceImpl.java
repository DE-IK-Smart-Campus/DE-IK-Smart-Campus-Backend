package hu.unideb.smartcampus.service.api.request.service;

import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.ExampleResponseWrapper;
import hu.unideb.smartcampus.shared.requestmessages.BaseRequestType;
import hu.unideb.smartcampus.shared.requestmessages.ExampleRequest;
import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;

/**
 * Example request processing service implementation.
 *
 */
@Component
public class ExampleRequestServiceImpl implements MessageProcessingClass<ExampleResponseWrapper> {

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

}

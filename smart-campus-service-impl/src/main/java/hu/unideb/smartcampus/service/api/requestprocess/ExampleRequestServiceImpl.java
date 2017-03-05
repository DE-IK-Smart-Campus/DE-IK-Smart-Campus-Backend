package hu.unideb.smartcampus.service.api.requestprocess;

import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.ExampleResponseWrapper;
import hu.unideb.smartcampus.shared.requestmessages.BaseRequestType;
import hu.unideb.smartcampus.shared.requestmessages.ExampleRequest;

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
    return ExampleResponseWrapper.builder().example(request.getExample()).build();
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Class<? extends BaseRequestType> getSupportedClass() {
    return ExampleRequest.class;
  }

}

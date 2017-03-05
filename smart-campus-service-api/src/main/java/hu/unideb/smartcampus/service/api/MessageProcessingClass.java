package hu.unideb.smartcampus.service.api;

import hu.unideb.smartcampus.service.api.domain.response.wrapper.BaseWrapper;
import hu.unideb.smartcampus.shared.requestmessages.BaseRequestType;

/**
 * Base interface of services which handles incoming message.
 *
 */
public interface MessageProcessingClass {

  /**
   * Generates a reply JSON.
   */
  <T extends BaseWrapper> T getResponse(Object object);

  /**
   * Supported class.
   */
  Class<? extends BaseRequestType> getSupportedClass();
}

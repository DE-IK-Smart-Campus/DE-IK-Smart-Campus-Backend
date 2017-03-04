package hu.unideb.smartcampus.service.api;

import hu.unideb.smartcampus.shared.message.BaseMessageType;

/**
 * Base interface of services which handles incoming message.
 *
 */
public interface MessageProcessingClass {

  /**
   * Generates a reply JSON.
   */
  String getJson(Object object);

  /**
   * Supported class.
   */
  Class<? extends BaseMessageType> getSupportedClass();
}

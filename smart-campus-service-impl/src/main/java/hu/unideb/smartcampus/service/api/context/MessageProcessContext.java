package hu.unideb.smartcampus.service.api.context;

import java.util.Map;

import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.shared.message.BaseMessageType;

/**
 * Base interface.
 *
 */
public interface MessageProcessContext {

  /**
   * Base method.
   */
  Map<Class<? extends BaseMessageType>, Class<? extends MessageProcessingClass>> getWebServiceMethods();
}

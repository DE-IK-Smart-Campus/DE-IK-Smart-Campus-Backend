package hu.unideb.smartcampus.service.api.context;

import java.util.Map;

import hu.unideb.smartcampus.shared.requestmessages.BaseRequestType;

/**
 * Intrerface of message process context.
 *
 */
public interface MessageProcessContext {

  /**
   * Base method.
   */
  Map<Class<? extends BaseRequestType>,String> getMessageServices();
}

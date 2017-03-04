package hu.unideb.smartcampus.service.api;

import hu.unideb.smartcampus.shared.exception.ProcessMessageException;

/**
 * Incoming message processing service, it should be injected to DefaultUser.
 */
public interface MessageProcessingService {

  /**
   * Process incoming message and returns a result JSON.
   */
  String processMessage(String message) throws ProcessMessageException;

}

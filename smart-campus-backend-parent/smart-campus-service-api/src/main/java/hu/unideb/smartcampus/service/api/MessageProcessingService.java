package hu.unideb.smartcampus.service.api;

import hu.unideb.smartcampus.shared.exception.ProcessMessageException;
import hu.unideb.smartcampus.shared.wrapper.BaseWrapper;

/**
 * Incoming message processing service, it should be injected to DefaultUser.
 */
public interface MessageProcessingService {

  /**
   * Process incoming message and returns a result JSON.
   */
  <T extends BaseWrapper> T processMessage(String message) throws ProcessMessageException;

}

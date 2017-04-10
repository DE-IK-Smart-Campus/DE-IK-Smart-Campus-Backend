package hu.unideb.smartcampus.service.api;

import java.io.IOException;

/**
 * Subject event service.
 */
public interface AsyncSubjectEventService {

  /**
   * Save subjects to student with Async call.
   */
  void saveSubjectEventsAsync(final String neptunIdentifier, final String userName)
      throws IOException;
}

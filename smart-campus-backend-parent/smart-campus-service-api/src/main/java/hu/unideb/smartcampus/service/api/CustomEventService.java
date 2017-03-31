package hu.unideb.smartcampus.service.api;

import java.util.List;

import hu.unideb.smartcampus.shared.iq.request.CustomEventListIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

/**
 * Custom event service.
 */
public interface CustomEventService {

  /**
   * Get custom events by IQ request.
   */
  List<CustomEventIqElement> getCustomEventsByIq(CustomEventListIqRequest iq);

}

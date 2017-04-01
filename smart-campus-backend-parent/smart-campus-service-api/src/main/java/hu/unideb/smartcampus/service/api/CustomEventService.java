package hu.unideb.smartcampus.service.api;

import java.util.List;

import hu.unideb.smartcampus.shared.iq.request.AddCustomEventIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListCustomEventIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

/**
 * Custom event service.
 */
public interface CustomEventService {

  /**
   * Get custom events by IQ request.
   */
  List<CustomEventIqElement> getCustomEventsByIq(ListCustomEventIqRequest iq);
  
  /**
   * Add custom event to user. 
   */
  void addCustomEntityByIq(AddCustomEventIqRequest iq);

}

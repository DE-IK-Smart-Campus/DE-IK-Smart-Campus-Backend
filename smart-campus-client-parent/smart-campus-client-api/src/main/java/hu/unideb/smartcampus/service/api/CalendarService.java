package hu.unideb.smartcampus.service.api;

import java.util.List;
import hu.unideb.smartcampus.domain.calendar.CalendarEvent;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

/**
 * Calendar service to use IQ.
 */
public interface CalendarService {
  List<CalendarEvent> getCalendarSubjectEvents(Long startPeriod, Long endPeriod);

  List<CalendarEvent> getCustomEvents();

  void addCustomEvent(CustomEventIqElement customEvent);

  void deleteCustomEvent(String eventGuid);
}

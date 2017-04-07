package hu.unideb.smartcampus.service.api;

import java.util.List;

import hu.unideb.smartcampus.shared.iq.request.CalendarSubjectsIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;

/**
 * Calendar subject service.
 */
public interface CalendarSubjectService {

  /**
   * Get subject events by IQ request.
   */
  List<CalendarSubjectIqElement> getSubjectEvents(CalendarSubjectsIqRequest iq);
  
  /**
   * Get subject events within period by IQ request.
   */
  List<CalendarSubjectIqElement> getSubjectEventsWithinPeriod(CalendarSubjectsIqRequest iq);
  
}

package hu.unideb.smartcampus.service.api;

import java.util.List;
import hu.unideb.smartcampus.domain.calendar.CalendarEvent;

/**
 * Calendar service to use IQ.
 */
public interface CalendarService {

  List<CalendarEvent> getCalendarSubjectEvents(Long startPeriod, Long endPeriod);
}

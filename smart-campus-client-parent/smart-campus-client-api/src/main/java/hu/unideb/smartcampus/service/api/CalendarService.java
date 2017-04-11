package hu.unideb.smartcampus.service.api;

import java.util.List;
import hu.unideb.smartcampus.domain.calendar.CalendarSubject;

/**
 * Calendar service to use IQ.
 */
public interface CalendarService {

  List<CalendarSubject> getCalendarSubjects(Long startPeriod, Long endPeriod);
}

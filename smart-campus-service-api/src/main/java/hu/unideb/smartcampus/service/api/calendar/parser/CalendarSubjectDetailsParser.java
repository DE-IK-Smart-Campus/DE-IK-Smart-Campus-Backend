package hu.unideb.smartcampus.service.api.calendar.parser;

import net.fortuna.ical4j.model.component.VEvent;

import hu.unideb.smartcampus.service.api.UnparsableCalendarEventSummaryException;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;

/**
 * Calendar subject details parser.
 */
public interface CalendarSubjectDetailsParser {

  /**
   * Parse subject details.
   */
  SubjectDetails parseSubjectDetails(VEvent vEvent) throws UnparsableCalendarEventSummaryException;
}

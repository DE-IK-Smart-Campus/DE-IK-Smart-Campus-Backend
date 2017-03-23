package hu.unideb.smartcampus.service.api;

/**
 * Parser to parse summary of a calendar event.
 *
 */
public interface CalendarSummaryParser {

  /**
   * Parses the summary of a calendar event.
   * 
   * @param summaryAsString the summary as a string
   * @return the parsed summary
   * @throws UnparsableCalendarEventSummaryException if the summary does not match for the pattern
   */
  CaledarEventSummary parseSummary(String summaryAsString)
      throws UnparsableCalendarEventSummaryException;

}

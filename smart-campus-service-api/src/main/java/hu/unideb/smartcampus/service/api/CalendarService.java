package hu.unideb.smartcampus.service.api;

import hu.unideb.smartcampus.service.api.exception.InputParseException;

/**
 * A service for Calendar realted operations.
 *
 */
public interface CalendarService {

  /**
   * Downloads, parses and processes the .ics file given.
   * 
   * @param url of the .ics file to be parsed
   * @throws InputParseException if any problem occours during the process
   */
  void downloadCalendar(String url) throws InputParseException;

}

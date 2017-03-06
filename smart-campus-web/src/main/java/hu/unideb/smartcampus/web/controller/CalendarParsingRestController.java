package hu.unideb.smartcampus.web.controller;

import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.service.api.CalendarService;
import hu.unideb.smartcampus.service.api.exception.InputParseException;

/**
 * Sample controller for .ics parsing.
 *
 */
@RestController
public class CalendarParsingRestController {
  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CalendarParsingRestController.class);

  /**
   * CalendarService.
   */
  @Autowired
  private CalendarService calendarService;

  /**
   * Sample call for .ics calendar parsing
   * 
   * @param url the url of the calendar
   * @return response
   */
  @PostMapping(path = "/processCalendar")
  public ResponseEntity processCalendarUrl(@QueryParam("url") String url) {
    try {
      calendarService.downloadCalendar(url);
      return ResponseEntity.ok().build();
    } catch (InputParseException e) {
      LOGGER.info("Cannot parse calendar", e);
      return ResponseEntity.badRequest().build();
    }
  }

}

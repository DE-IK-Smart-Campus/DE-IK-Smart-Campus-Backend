package hu.unideb.smartcampus.service.api.calendar.parser;

import net.fortuna.ical4j.model.component.VEvent;

import java.time.LocalDateTime;

/**
 * Calendar event parse helper.
 */
public interface CalendarLocalDateTimeParser {

  /**
   * Create start date time as localDateTime from vEvent.
   */
  LocalDateTime parseStartLocalDateTime(VEvent vEvent);

  /**
   * Create end date time as localDateTime from vEvent.
   */
  LocalDateTime parseEndLocalDateTime(VEvent vEvent);
}
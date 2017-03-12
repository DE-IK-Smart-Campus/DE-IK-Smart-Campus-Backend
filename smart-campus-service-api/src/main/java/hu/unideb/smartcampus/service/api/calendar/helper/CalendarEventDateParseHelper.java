package hu.unideb.smartcampus.service.api.calendar.helper;

import net.fortuna.ical4j.model.component.VEvent;

import java.time.LocalDateTime;

/**
 * Calendar event parse helper.
 */
public interface CalendarEventDateParseHelper {

  /**
   * Create start date time as localDateTime from vEvent.
   */
  LocalDateTime getStartDateTimeFromEvent(VEvent vEvent);

  /**
   * Create end date time as localDateTime from vEvent.
   */
  LocalDateTime getEndDateTimeFromEvent(VEvent vEvent);
}
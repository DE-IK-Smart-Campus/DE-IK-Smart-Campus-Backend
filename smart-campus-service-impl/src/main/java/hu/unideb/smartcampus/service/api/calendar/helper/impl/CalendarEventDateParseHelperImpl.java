package hu.unideb.smartcampus.service.api.calendar.helper.impl;

import net.fortuna.ical4j.model.component.VEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import hu.unideb.smartcampus.service.api.calendar.helper.CalendarEventDateParseHelper;

/**
 * Implementation for {@link CalendarEventDateParseHelper}.
 **/
@Component
public class CalendarEventDateParseHelperImpl implements CalendarEventDateParseHelper {

  /**
   * {@inheritDoc}.
   */
  @Override
  public LocalDateTime getStartDateTimeFromEvent(final VEvent vEvent) {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(vEvent.getStartDate().getDate().getTime()),
        ZoneId.systemDefault());
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public LocalDateTime getEndDateTimeFromEvent(final VEvent vEvent) {
    return  LocalDateTime.ofInstant(Instant.ofEpochMilli(vEvent.getEndDate(true).getDate().getTime()),
        ZoneId.systemDefault());
  }
}
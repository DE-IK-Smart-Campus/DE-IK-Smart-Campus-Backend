package hu.unideb.smartcampus.service.api.calendar.parser.impl;

import net.fortuna.ical4j.model.component.VEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import hu.unideb.smartcampus.service.api.calendar.domain.subject.AppointmentTime;
import hu.unideb.smartcampus.service.api.calendar.parser.CalendarAppointmentDetailsParser;
import hu.unideb.smartcampus.service.api.calendar.parser.CalendarLocalDateTimeParser;


@Component
public class CalendarAppointmentDetailsParserImpl implements CalendarAppointmentDetailsParser {

  private final CalendarLocalDateTimeParser calendarLocalDateTimeParser;

  @Autowired
  public CalendarAppointmentDetailsParserImpl(final CalendarLocalDateTimeParser calendarLocalDateTimeParser) {
    this.calendarLocalDateTimeParser = calendarLocalDateTimeParser;
  }

  @Override
  public AppointmentTime parseAppointmentDetails(final VEvent vEvent) {
    validateVEventByLocation(vEvent);
    return AppointmentTime.builder()
        .startDateTime(this.calendarLocalDateTimeParser.parseStartLocalDateTime(vEvent))
        .endDateTime(this.calendarLocalDateTimeParser.parseEndLocalDateTime(vEvent))
        .build();
  }

  private void validateVEventByLocation(final VEvent vEvent) {
    Assert.notNull(vEvent);
    Assert.notNull(vEvent.getLocation());
  }
}

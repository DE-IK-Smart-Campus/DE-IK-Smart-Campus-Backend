package hu.unideb.smartcampus.service.api.calendar.parser.impl;

import net.fortuna.ical4j.model.component.VEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import hu.unideb.smartcampus.service.api.calendar.domain.subject.AppointmentDetails;
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
  public AppointmentDetails parseAppointmentDetails(final VEvent vEvent) {
    validateVEventByLocation(vEvent);
    return AppointmentDetails.builder()
        .startDate(this.calendarLocalDateTimeParser.parseStartLocalDateTime(vEvent))
        .endDate(this.calendarLocalDateTimeParser.parseEndLocalDateTime(vEvent))
        .roomLocation(vEvent.getLocation().getValue())
        .build();
  }

  private void validateVEventByLocation(final VEvent vEvent) {
    Assert.notNull(vEvent);
    Assert.notNull(vEvent.getLocation());
  }
}

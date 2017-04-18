package hu.unideb.smartcampus.service.api.converter.calendar;

import org.springframework.core.convert.converter.Converter;

import java.time.format.DateTimeFormatter;
import hu.unideb.smartcampus.domain.calendar.CalendarEvent;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;
import hu.unideb.smartcampus.shared.util.DateUtil;

public class CalendarEventConverter implements Converter<CustomEventIqElement, CalendarEvent> {
  @Override
  public CalendarEvent convert(CustomEventIqElement customEventIqElement) {
    return new CalendarEvent(
        customEventIqElement.getEventName(),
        DateUtil.getInLocalDateTimeByEpochSecond(customEventIqElement.getEventStart()).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
        DateUtil.getInLocalDateTimeByEpochSecond(customEventIqElement.getEventEnd()).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    );
  }
}

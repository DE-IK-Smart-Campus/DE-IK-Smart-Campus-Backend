package hu.unideb.smartcampus.service.api.converter.calendar;

import org.springframework.core.convert.converter.Converter;

import hu.unideb.smartcampus.domain.calendar.CalendarEvent;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

public class CalendarEventConverter implements Converter<CustomEventIqElement, CalendarEvent> {
  @Override
  public CalendarEvent convert(CustomEventIqElement customEventIqElement) {
    return new CalendarEvent(
        customEventIqElement.getEventName(),
        customEventIqElement.getEventStart().toString(),
        customEventIqElement.getEventEnd().toString()
    );
  }
}

package hu.unideb.smartcampus.service.api.converter.calendar;

import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;
import hu.unideb.smartcampus.domain.calendar.CalendarEvent;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

public class ListCustomEventToCalendarEventListConverter implements Converter<List<CustomEventIqElement>, List<CalendarEvent>> {
  private Converter<CustomEventIqElement, CalendarEvent> converter;

  public ListCustomEventToCalendarEventListConverter() {
    this.converter = new CalendarEventConverter();
  }

  @Override
  public List<CalendarEvent> convert(List<CustomEventIqElement> customEventIqElements) {
    return customEventIqElements.stream().map(converter::convert).collect(Collectors.toList());
  }
}

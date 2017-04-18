package hu.unideb.smartcampus.service.api.converter.calendar;

import org.springframework.core.convert.converter.Converter;

import hu.unideb.smartcampus.domain.calendar.CustomEvent;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

public class CustomEventConverter implements Converter<CustomEventIqElement, CustomEvent> {
  @Override
  public CustomEvent convert(CustomEventIqElement customEventIqElement) {
    return new CustomEvent(
        customEventIqElement.getGuid(),
        customEventIqElement.getEventName(),
        customEventIqElement.getEventDescription(),
        customEventIqElement.getEventPlace(),
        customEventIqElement.getEventStart(),
        customEventIqElement.getEventEnd(),
        customEventIqElement.getEventRepeat(),
        customEventIqElement.getReminder()
    );
  }
}

package hu.unideb.smartcampus.service.api.converter.toiq;

import java.time.ZoneOffset;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.CustomEventEntity;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

/**
 * Custom event entity to Custom event IQ.
 */
@Component
public class CustomEventEntityToCustomEventIq
    implements Converter<CustomEventEntity, CustomEventIqElement> {

  @Override
  public CustomEventIqElement convert(CustomEventEntity source) {
    return CustomEventIqElement.builder()
        .eventId(source.getId())
        .eventName(source.getEventName())
        .eventPlace(source.getEventName())
        .eventDescription(source.getEventName())
        .eventStart(source.getEventStart().toEpochSecond(ZoneOffset.ofHours(1)))
        .eventEnd(source.getEventEnd().toEpochSecond(ZoneOffset.ofHours(1)))
        .reminder(source.getReminder())
        .build();
  }

}

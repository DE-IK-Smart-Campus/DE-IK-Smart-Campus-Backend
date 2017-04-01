package hu.unideb.smartcampus.service.api.converter.toentity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.CustomEventEntity;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

/**
 * Custom event IQ to Custom event entity.
 */
@Component
public class CustomEventIqToCustomEventEntity
    implements Converter<CustomEventIqElement, CustomEventEntity> {

  @Override
  public CustomEventEntity convert(CustomEventIqElement source) {
    return CustomEventEntity.builder()
        .eventName(source.getEventName())
        .eventPlace(source.getEventName())
        .eventDescription(source.getEventName())
        .eventStart(getLocalDateTime(source.getEventStart()))
        .eventEnd(getLocalDateTime(source.getEventEnd()))
        .reminder(source.getReminder())
        .build();
  }

  private LocalDateTime getLocalDateTime(Long source) {
    return source != null
        ? Instant.ofEpochMilli(source).atZone(ZoneOffset.ofHours(1)).toLocalDateTime()
        : LocalDateTime.MIN;
  }

}

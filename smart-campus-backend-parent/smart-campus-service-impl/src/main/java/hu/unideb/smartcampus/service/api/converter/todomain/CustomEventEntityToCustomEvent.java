package hu.unideb.smartcampus.service.api.converter.todomain;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.CustomEventEntity;
import hu.unideb.smartcampus.service.api.domain.CustomEvent;

@Component
public class CustomEventEntityToCustomEvent implements Converter<CustomEventEntity, CustomEvent> {

  @Override
  public CustomEvent convert(CustomEventEntity source) {
    return CustomEvent.builder()
        .guid(source.getGuid())
        .eventName(source.getEventName())
        .eventDescription(source.getEventDescription())
        .eventPlace(source.getEventPlace())
        .eventRepeat(source.getEventRepeat())
        .eventStart(source.getEventStart())
        .eventEnd(source.getEventEnd())
        .reminder(source.getReminder())
        .build();
  }

}

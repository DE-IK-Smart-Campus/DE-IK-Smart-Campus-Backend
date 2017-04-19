package hu.unideb.smartcampus.service.api.converter.toentity;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.CustomEventEntity;
import hu.unideb.smartcampus.service.api.domain.CustomEvent;

@Component
public class CustomEventToCustomEventEntity implements Converter<CustomEvent, CustomEventEntity> {

  @Override
  public CustomEventEntity convert(CustomEvent source) {
    return CustomEventEntity.builder()
        .id(source.getId())
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

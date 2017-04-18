package hu.unideb.smartcampus.service.api.converter.toentity;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.CustomEventEntity;
import hu.unideb.smartcampus.service.api.util.DateUtil;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

/**
 * Custom event IQ to Custom event entity.
 */
@Component
public class CustomEventIqToCustomEventEntity
    implements Converter<CustomEventIqElement, CustomEventEntity> {

  private final DateUtil dateUtil;

  /**
   * Constructor.
   */
  @Autowired
  public CustomEventIqToCustomEventEntity(DateUtil dateUtil) {
    this.dateUtil = dateUtil;
  }

  @Override
  public CustomEventEntity convert(CustomEventIqElement source) {
    return CustomEventEntity.builder()
        .guid(source.getGuid())
        .eventName(source.getEventName())
        .eventPlace(source.getEventPlace())
        .eventDescription(source.getEventDescription())
        .eventRepeat(source.getEventRepeat())
        .eventWhen(getLocalDateTime(source.getEventWhen()).toLocalDate())
        .eventStart(getLocalDateTime(source.getEventStart()))
        .eventEnd(getLocalDateTime(source.getEventEnd()))
        .reminder(source.getReminder())
        .build();
  }

  private LocalDateTime getLocalDateTime(Long source) {
    return source != null
        ? dateUtil.getInLocalDateTimeByEpochSecond(source)
        : LocalDateTime.MIN;
  }

}

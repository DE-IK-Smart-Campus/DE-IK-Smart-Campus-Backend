package hu.unideb.smartcampus.service.api.converter.toiq;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import hu.unideb.smartcampus.persistence.entity.CustomEventEntity;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;
import hu.unideb.smartcampus.shared.util.DateUtil;

/**
 * Custom event entity to Custom event IQ.
 */
@Component
public class CustomEventEntityToCustomEventIq
    implements Converter<CustomEventEntity, CustomEventIqElement> {

  @Override
  public CustomEventIqElement convert(CustomEventEntity source) {
    return CustomEventIqElement.builder()
        .guid(source.getGuid())
        .eventName(source.getEventName())
        .eventPlace(source.getEventPlace())
        .eventDescription(source.getEventDescription())
        .eventRepeat(source.getEventRepeat())
        .eventWhen(getInLong(source.getEventWhen().atStartOfDay()))
        .eventStart(getInLong(source.getEventStart()))
        .eventEnd(getInLong(source.getEventEnd()))
        .reminder(source.getReminder())
        .build();
  }

  private Long getInLong(LocalDateTime source) {
    if (source == null)
      return null;
    return DateUtil.getInEpochLongByLocalDateTime(source);
  }

}

package hu.unideb.smartcampus.service.api.converter.calendar;

import org.springframework.core.convert.converter.Converter;

import hu.unideb.smartcampus.domain.calendar.CustomEvent;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;
import hu.unideb.smartcampus.shared.util.DateUtil;

public class CustomEventConverter implements Converter<CustomEventIqElement, CustomEvent> {
  @Override
  public CustomEvent convert(CustomEventIqElement customEventIqElement) {
    return new CustomEvent(
        customEventIqElement.getGuid(),
        customEventIqElement.getEventName(),
        customEventIqElement.getEventDescription(),
        customEventIqElement.getEventPlace(),
        DateUtil.getInLocalDateTimeByEpochSecond(customEventIqElement.getEventStart()),
        DateUtil.getInLocalDateTimeByEpochSecond(customEventIqElement.getEventEnd()),
        DateUtil.getInLocalDateByEpochSecond(customEventIqElement.getEventWhen()),
        customEventIqElement.getEventRepeat(),
        customEventIqElement.getReminder()
    );
  }
}

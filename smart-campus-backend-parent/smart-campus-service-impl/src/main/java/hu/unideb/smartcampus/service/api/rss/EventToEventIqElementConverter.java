package hu.unideb.smartcampus.service.api.rss;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.shared.iq.request.element.EventIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.LocationIqElement;

@Component
public class EventToEventIqElementConverter implements Converter<Event, EventIqElement> {

  @Autowired
  private LocationToLocationIqElementConverter locationConverter;

  @Override
  public EventIqElement convert(Event source) {
    LocationIqElement location = null;
    if (source.getLocation() != null) {
      location = locationConverter.convert(source.getLocation());
    }

    return EventIqElement.builder().description(source.getDescription())
        .endTime(eventTimeToLong(source.getEndTime()))
        .startTime(eventTimeToLong(source.getStartTime())).eventId(source.getEventId())
        .name(source.getName()).location(location).build();
  }

  private Long eventTimeToLong(ZonedDateTime date) {
    if (date == null) {
      return null;
    }
    return date.toEpochSecond();
  }

}

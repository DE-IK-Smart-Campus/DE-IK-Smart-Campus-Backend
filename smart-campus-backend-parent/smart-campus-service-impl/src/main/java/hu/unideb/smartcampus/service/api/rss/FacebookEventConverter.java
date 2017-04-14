package hu.unideb.smartcampus.service.api.rss;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalQueries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.service.api.rss.facebook.FacebookEvent;

@Component
public class FacebookEventConverter implements Converter<FacebookEvent, Event> {

  private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
  @Autowired
  private LocationConverter locationConverter;

  @Override
  public Event convert(FacebookEvent source) {
    return Event.builder().description(source.getDescription())
        .endTime(parseDate(source.getEndTime())).startTime(parseDate(source.getStartTime()))
        .eventId(source.getId()).name(source.getName())
        .location(locationConverter.convert(source.getPlace())).build();
  }

  public LocalDateTime parseDate(String date){
    if (date == null){
      return null;
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
    return LocalDateTime.parse(date, formatter);  
  }

}

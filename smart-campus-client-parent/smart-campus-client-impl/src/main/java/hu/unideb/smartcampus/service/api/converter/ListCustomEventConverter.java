package hu.unideb.smartcampus.service.api.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;
import hu.unideb.smartcampus.domain.calendar.CalendarEvent;
import hu.unideb.smartcampus.shared.iq.request.ListCustomEventIqRequest;

public class ListCustomEventConverter implements Converter<ListCustomEventIqRequest, List<CalendarEvent>> {

  private CustomEventConverter customEventConverter;

  public ListCustomEventConverter() {
    this.customEventConverter = new CustomEventConverter();
  }

  @Override
  public List<CalendarEvent> convert(ListCustomEventIqRequest listCustomEventIqRequest) {
    return listCustomEventIqRequest.getCustomEvents().stream().map(customEventConverter::convert).collect(Collectors.toList());
  }
}

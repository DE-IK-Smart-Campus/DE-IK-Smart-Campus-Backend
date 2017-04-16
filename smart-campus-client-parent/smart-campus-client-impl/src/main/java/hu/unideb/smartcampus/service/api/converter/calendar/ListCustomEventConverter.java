package hu.unideb.smartcampus.service.api.converter.calendar;

import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;
import hu.unideb.smartcampus.domain.calendar.CustomEvent;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

public class ListCustomEventConverter implements Converter<List<CustomEventIqElement>, List<CustomEvent>> {

  private CustomEventConverter customEventConverter;

  public ListCustomEventConverter() {
    this.customEventConverter = new CustomEventConverter();
  }

  @Override
  public List<CustomEvent> convert(List<CustomEventIqElement> customEventIqElements) {
    return customEventIqElements.stream().map(customEventConverter::convert).collect(Collectors.toList());
  }
}

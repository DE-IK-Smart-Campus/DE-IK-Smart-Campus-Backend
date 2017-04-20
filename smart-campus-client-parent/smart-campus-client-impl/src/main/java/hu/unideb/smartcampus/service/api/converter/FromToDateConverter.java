package hu.unideb.smartcampus.service.api.converter;

import java.sql.Timestamp;

import org.springframework.core.convert.converter.Converter;

import hu.unideb.smartcampus.shared.iq.request.element.FromToDateIqElement;

public class FromToDateConverter implements Converter<FromToDateIqElement, Timestamp[]> {
  @Override
  public Timestamp[] convert(FromToDateIqElement fromToDateIqElement) {
    return new Timestamp[] {
        new Timestamp(fromToDateIqElement.getFrom() * 1000),
        new Timestamp(fromToDateIqElement.getTo() * 1000)};
  }
}

package hu.unideb.smartcampus.service.api.converter;

import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import hu.unideb.smartcampus.shared.iq.request.element.FromToDateIqElement;

public class FromToDateConverter implements Converter<FromToDateIqElement, Timestamp[]> {
  @Override
  public Timestamp[] convert(FromToDateIqElement fromToDateIqElement) {
    return new Timestamp[]{
        new Timestamp(fromToDateIqElement.getFrom()),
        new Timestamp(fromToDateIqElement.getTo())};
  }
}

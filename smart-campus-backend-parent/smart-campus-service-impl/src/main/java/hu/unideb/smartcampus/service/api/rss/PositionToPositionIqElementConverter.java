package hu.unideb.smartcampus.service.api.rss;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.shared.iq.request.element.PositionIqElement;

@Component
public class PositionToPositionIqElementConverter
    implements Converter<Position, PositionIqElement> {

  @Override
  public PositionIqElement convert(Position source) {
    return PositionIqElement.builder()
        .city(source.getCity())
        .country(source.getCountry())
        .latitude(source.getLatitude())
        .longitude(source.getLongitude())
        .street(source.getStreet())
        .zipCode(source.getZipCode())
        .build();
  }

}

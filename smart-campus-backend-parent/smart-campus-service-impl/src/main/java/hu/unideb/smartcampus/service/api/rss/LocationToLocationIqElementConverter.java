package hu.unideb.smartcampus.service.api.rss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.shared.iq.request.element.LocationIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.PositionIqElement;

@Component
public class LocationToLocationIqElementConverter
    implements Converter<Location, LocationIqElement> {

  @Autowired
  PositionToPositionIqElementConverter positionConverter;

  @Override
  public LocationIqElement convert(Location source) {

    PositionIqElement position = positionConverter.convert(source.getPosition());

    return LocationIqElement.builder().id(source.getId()).name(source.getName()).position(position)
        .build();
  }

}

package hu.unideb.smartcampus.service.api.rss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.service.api.rss.facebook.FacebookEventPlace;

@Component
public class LocationConverter implements Converter<FacebookEventPlace, Location> {

  @Autowired
  private PositionConverter positionConverter;
  
  @Override
  public Location convert(FacebookEventPlace source) {
    if (source == null){
      return null;
    }
    return Location.builder().id(source.getId()).name(source.getName()).position(positionConverter.convert(source.getLocation())).build();
  }

}

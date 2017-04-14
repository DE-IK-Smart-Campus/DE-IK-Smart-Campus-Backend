package hu.unideb.smartcampus.service.api.rss;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.service.api.rss.facebook.FaceBookEventLocation;

@Component
public class PositionConverter implements Converter<FaceBookEventLocation, Position> {

  @Override
  public Position convert(FaceBookEventLocation source) {
    if (source == null) {
      return null;
    }
    return Position.builder().city(source.getCity()).country(source.getCountry())
        .latitude(source.getLatitude()).longitude(source.getLongitude()).street(source.getStreet())
        .zipCode(source.getZipCode()).build();
  }

}

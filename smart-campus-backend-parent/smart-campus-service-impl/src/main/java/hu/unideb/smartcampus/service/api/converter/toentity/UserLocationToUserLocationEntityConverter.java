package hu.unideb.smartcampus.service.api.converter.toentity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.mongo.entity.UserLocationEntity;
import hu.unideb.smartcampus.service.api.domain.UserLocation;

@Component
public class UserLocationToUserLocationEntityConverter implements Converter<UserLocation, UserLocationEntity>{

  @Override
  public UserLocationEntity convert(UserLocation source) {
    Circle location = new Circle(new Point(source.getLatitude(),source.getLongitude()), source.getAccuracy()); 
    UserLocationEntity entity = UserLocationEntity.builder().id(source.getId()).timestamp(source.getTimestamp()).userId(source.getUser().getId()).location(location).build();
    return entity;
  }

}

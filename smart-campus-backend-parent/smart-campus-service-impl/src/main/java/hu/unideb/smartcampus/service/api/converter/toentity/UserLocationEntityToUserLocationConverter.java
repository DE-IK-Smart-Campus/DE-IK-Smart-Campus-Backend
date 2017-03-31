package hu.unideb.smartcampus.service.api.converter.toentity;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.mongo.entity.UserLocationEntity;
import hu.unideb.smartcampus.service.api.UserService;
import hu.unideb.smartcampus.service.api.domain.User;
import hu.unideb.smartcampus.service.api.domain.UserLocation;

@Component
public class UserLocationEntityToUserLocationConverter
    implements Converter<UserLocationEntity, UserLocation> {

  @Autowired
  private UserService userService;
  
  @Override
  public UserLocation convert(UserLocationEntity source) {
    final Optional<User> user = userService.getById(source.getUserId());
    
    UserLocation location = UserLocation.builder()
        .accuracy(source.getLocation().getRadius().getValue())
        .longitude(source.getLocation().getCenter().getY())
        .latitude(source.getLocation().getCenter().getX())
        .id(source.getId())
        .timestamp(source.getTimestamp())
        .user(user.get())
      .build();
    return location;
  }

}

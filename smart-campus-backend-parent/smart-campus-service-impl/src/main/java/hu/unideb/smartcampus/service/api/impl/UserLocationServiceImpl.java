package hu.unideb.smartcampus.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.persistence.mongo.entity.UserLocationEntity;
import hu.unideb.smartcampus.persistence.mongo.repository.UserLocationRepository;
import hu.unideb.smartcampus.service.api.UserLocationService;
import hu.unideb.smartcampus.service.api.converter.toentity.UserLocationEntityToUserLocationConverter;
import hu.unideb.smartcampus.service.api.converter.toentity.UserLocationToUserLocationEntityConverter;
import hu.unideb.smartcampus.service.api.domain.UserLocation;

@Service
public class UserLocationServiceImpl implements UserLocationService {

  @Autowired
  private UserLocationRepository userLocationRepository;

  @Autowired
  private UserLocationEntityToUserLocationConverter userLocationEntityToUserLocationConverter;
  
  @Autowired
  private UserLocationToUserLocationEntityConverter userLocationToUserLocationEntityConverter;

  @Override
  public void save(UserLocation location) {
    
    UserLocationEntity persistedEntity = userLocationRepository.save(userLocationToUserLocationEntityConverter.convert(location));
    
  }

}

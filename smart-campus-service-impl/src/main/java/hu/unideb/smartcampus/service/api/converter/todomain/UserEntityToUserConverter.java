package hu.unideb.smartcampus.service.api.converter.todomain;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.UserEntity;
import hu.unideb.smartcampus.service.api.domain.User;

/**
 * {@inheritDoc}
 */
@Component
public class UserEntityToUserConverter implements Converter<UserEntity, User> {

  /**
   * {@inheritDoc}
   */
  @Override
  public User convert(final UserEntity userEntity) {
    return userEntity == null ? null :
        User.builder()
            .id(userEntity.getId())
            .username(userEntity.getUsername())
            .password(userEntity.getPassword())
            .role(userEntity.getRole())
            .build();
  }
}

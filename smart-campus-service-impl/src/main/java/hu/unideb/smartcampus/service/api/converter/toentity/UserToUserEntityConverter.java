package hu.unideb.smartcampus.service.api.converter.toentity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.UserEntity;
import hu.unideb.smartcampus.service.api.domain.User;

/**
 * {@inheritDoc}
 */
@Component
public class UserToUserEntityConverter implements Converter<User, UserEntity> {

  /**
   * {@inheritDoc}
   */
  @Override
  public UserEntity convert(final User user) {
    return user == null ? null :
        UserEntity.builder()
            .id(user.getId())
            .username(user.getUsername())
            .password(user.getPassword())
            .role(user.getRole())
            .build();
  }
}

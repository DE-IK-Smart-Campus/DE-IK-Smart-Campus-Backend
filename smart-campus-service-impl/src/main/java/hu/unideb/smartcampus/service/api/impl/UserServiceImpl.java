package hu.unideb.smartcampus.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;
import hu.unideb.smartcampus.persistence.repository.UserRepository;
import hu.unideb.smartcampus.service.api.UserService;
import hu.unideb.smartcampus.service.api.domain.User;

/**
 * Implementation for {@link UserService}.
 */
@Service
public class UserServiceImpl implements UserService {

  /**
   * UserRepository.
   */
  private final UserRepository userRepository;
  /**
   * ConversionService.
   */
  private final ConversionService conversionService;

  /**
   * UserServiceImpl constructor.
   */
  @Autowired
  public UserServiceImpl(final UserRepository userRepository, final ConversionService conversionService) {
    this.userRepository = userRepository;
    this.conversionService = conversionService;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Optional<User> getByUsername(final String username) {
    Assert.notNull(username);

    return Optional.ofNullable(this.conversionService.convert(this.userRepository.findByUsername(username), User.class));
  }
}

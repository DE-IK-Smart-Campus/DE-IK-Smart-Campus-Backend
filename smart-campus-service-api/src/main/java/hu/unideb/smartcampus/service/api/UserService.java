package hu.unideb.smartcampus.service.api;

import java.util.Optional;
import hu.unideb.smartcampus.service.api.domain.User;

/**
 * UserService.
 */
public interface UserService {

  /**
   * Retrieves {@link User} in {@link Optional}.
   */
  Optional<User> getByUsername(String username);
}

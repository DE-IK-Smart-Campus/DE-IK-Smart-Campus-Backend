package hu.unideb.smartcampus.service.api;

import java.util.Optional;
import hu.unideb.smartcampus.service.api.domain.User;

/**
 * UserService.
 */
public interface UserService {

  Optional<User> getById(Long id);

  /**
   * Retrieves {@link User} in {@link Optional}.
   */
  Optional<User> getByUsername(String username);

  /**
   * Saves the user.
   * 
   * @param user to be saved
   * @return the persisted entity with its id set
   */
  User save(User user);
}

package hu.unideb.smartcampus.service.api.domain;

import hu.unideb.smartcampus.shared.enumeration.Role;
import lombok.Builder;
import lombok.Data;

/**
 * User domain object.
 */
@Data
public class User extends BaseObject<Long> {

  /**
   * The username of the user.
   */
  private final String username;
  /**
   * The password of the user.
   */
  private final String password;
  /**
   * The role of the user.
   */
  private final Role role;

  /**
   * Builder pattern for creating user.
   * @param id the id of the user
   * @param username the username of the user
   * @param password the password of the user
   * @param role the role of the user
   */
  @Builder
  public User(final Long id, final String username, final String password, final Role role) {
    super(id);
    this.username = username;
    this.password = password;
    this.role = role;
  }
}

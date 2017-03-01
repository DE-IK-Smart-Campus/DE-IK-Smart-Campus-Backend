package hu.unideb.smartcampus.shared.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Class to hold Ejabberd REST Endpoints for User related operations.
 *
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityConstants {

  /**
   * The path of the user registering endpoint.
   */
  public static final String REGISTER_USER = "/register";

}

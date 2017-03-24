package hu.unideb.smartcampus.shared.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Class to hold Ejabberd REST Endpoints for User related operations.
 *
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EjabberdSecurityConstants {

  /**
   * The path of the user registering endpoint.
   */
  public static final String REGISTER_USER = "/register";
  
  /**
   * The path of the user account check service.
   */
  public static final String CHECK_ACCOUNT = "/check_account";

  /**
   * The path of the password change service.
   */
  public static final String CHANGE_PASSWORD = "/change_password";

}

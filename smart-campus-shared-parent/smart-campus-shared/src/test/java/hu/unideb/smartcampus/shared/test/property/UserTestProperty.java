package hu.unideb.smartcampus.shared.test.property;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * User test properties.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserTestProperty {

  /**
   * Id of the admin user.
   */
  public static final long USER_ID_ADMIN = 1L;
  /**
   * Username of the admin user.
   */
  public static final String USERNAME_ADMIN = "admin";
  /**
   * Password of the admin user.
   */
  public static final String PASSWORD_ADMIN = "$2y$10$fg3EtHh11XUc4bSbAIntNuajBgZDsCAHsiiUL2J2q3IYJUTln8Idy";
}

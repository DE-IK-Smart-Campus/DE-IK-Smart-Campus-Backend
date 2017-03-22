package hu.unideb.smartcampus.service.api;

import hu.unideb.smartcampus.shared.exception.RegistrationFailedException;

/**
 * The service of the user creation.
 *
 */
public interface UserRegistrationService {

  /**
   * Registrates the user with the username specified and a generated password.
   * 
   * @param username the username of the user
   * @throws RegistrationFailedException if the XMPP server did not responded as expected
   */
  void doRegister(String username) throws RegistrationFailedException;

}

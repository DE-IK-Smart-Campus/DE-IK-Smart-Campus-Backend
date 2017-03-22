package hu.unideb.smartcampus.service.api;

import hu.unideb.smartcampus.shared.exception.SmartCampusException;

/**
 * Interface for handling registration failure.
 *
 */
public interface UserAlreadyExistsRegistrationFailureHandlingStrategy {

  /**
   * Handles the failure of the registration process.
   * 
   * @param username the username of the user
   * @param password the password of the user
   * @throws SmartCampusException if any problem occurs during the process
   */
  void handle(String username, String password) throws SmartCampusException;

  /**
   * Checks if total registration failure needed.
   * 
   * @return {@code true} if retry of the registration needed, {@code false} otherwise
   */
  boolean failRegistration();
}

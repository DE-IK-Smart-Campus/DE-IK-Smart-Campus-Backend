package hu.unideb.smartcampus.shared.exception;

/**
 * Indicates that something went wrong during a creation of a user.
 *
 */
public class RegistrationFailedException extends Exception {

  /**
   * Constructor with message parameter.
   * 
   * @param message the message of the exception
   */
  public RegistrationFailedException(String message) {
    super(message);
  }

  /**
   * Constructor with cause parameter.
   * 
   * @param cause the cause of the exception
   */
  public RegistrationFailedException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructor with message and cause parameter.
   * 
   * @param message the message of the exception
   * @param cause the cause of the exception
   */
  public RegistrationFailedException(String message, Throwable cause) {
    super(message, cause);
  }


}

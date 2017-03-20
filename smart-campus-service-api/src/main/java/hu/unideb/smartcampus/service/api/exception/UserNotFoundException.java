package hu.unideb.smartcampus.service.api.exception;

/**
 * User not found exception.
 */
public class UserNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 578774199273368238L;

  /**
   * Constructor.
   */
  public UserNotFoundException(final String message) {
    super(message);
  }
}

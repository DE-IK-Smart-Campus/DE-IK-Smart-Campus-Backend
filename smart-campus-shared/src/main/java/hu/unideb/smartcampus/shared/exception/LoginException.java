package hu.unideb.smartcampus.shared.exception;

/**
 * Exception which thrown on login error.
 *
 *
 */
public class LoginException extends XmppException {

  private static final long serialVersionUID = 1L;

  /**
   * Constructs an empty LoginException.
   */
  public LoginException() {
    super();
  }

  /**
   * Constructs a LoginException with message.
   */
  public LoginException(String message) {
    super(message);
  }

  /**
   * Constructs a LoginException with message and cause.
   */
  public LoginException(String message, Throwable cause) {
    super(message, cause);
  }

}

package hu.unideb.smartcampus.shared.exception;

/**
 * Exception which thrown on connection error.
 *
 *
 */
public class ConnectionException extends XmppException {

  private static final long serialVersionUID = 1L;

  /**
   * Constructs an empty ConnectionException.
   */
  public ConnectionException() {
    super();
  }

  /**
   * Constructs a ConnectionException with message.
   */
  public ConnectionException(String message) {
    super(message);
  }

  /**
   * Constructs a ConnectionException with message and cause.
   */
  public ConnectionException(String message, Throwable cause) {
    super(message, cause);
  }

}

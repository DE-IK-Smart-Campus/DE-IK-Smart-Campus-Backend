package hu.unideb.smartcampus.service.api.exception;

/**
 * An exception class to indicate that an error happened during a parsing process.
 *
 */
public class InputParseException extends Exception {

  private static final long serialVersionUID = -178158630303557764L;

  /**
   * Default constructor.
   */
  public InputParseException() {
    super();
  }

  /**
   * Constructor with message and cause parameters.
   * 
   * @param message the message of the exception
   * @param cause the cause of the exception
   */
  public InputParseException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructor with message parameter.
   * 
   * @param message the message of the exception
   */
  public InputParseException(String message) {
    super(message);
  }

  /**
   * Constructor with cause parameter.
   * 
   * @param cause the cause of the exception
   */
  public InputParseException(Throwable cause) {
    super(cause);
  }

}

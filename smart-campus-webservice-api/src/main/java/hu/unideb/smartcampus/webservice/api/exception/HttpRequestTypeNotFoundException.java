package hu.unideb.smartcampus.webservice.api.exception;

/**
 * Indicates that http request type does not exist.
 */
public class HttpRequestTypeNotFoundException extends RuntimeException {

  /**
   * Serial version UID.
   */
  private static final long serialVersionUID = -5811828196279691011L;

  /**
   * Constructor.
   * @param message the message of the error.
   */
  public HttpRequestTypeNotFoundException(final String message) {
    super(message);
  }
}

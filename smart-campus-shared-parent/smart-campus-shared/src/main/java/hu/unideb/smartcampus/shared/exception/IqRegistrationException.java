package hu.unideb.smartcampus.shared.exception;

public class IqRegistrationException extends Exception {

  private static final long serialVersionUID = -178158630303557764L;

  /**
   * Default constructor.
   */
  public IqRegistrationException() {
    super();
  }

  /**
   * Constructor with message and cause parameters.
   * 
   * @param message the message of the exception
   * @param cause the cause of the exception
   */
  public IqRegistrationException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructor with message parameter.
   * 
   * @param message the message of the exception
   */
  public IqRegistrationException(String message) {
    super(message);
  }

  /**
   * Constructor with cause parameter.
   * 
   * @param cause the cause of the exception
   */
  public IqRegistrationException(Throwable cause) {
    super(cause);
  }
}

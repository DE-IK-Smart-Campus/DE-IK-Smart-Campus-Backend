package hu.unideb.smartcampus.shared.exception;

/**
 * Base exception for the application.
 *
 */
public class SmartCampusException extends Exception {

  /**
   * Serial version.
   */
  private static final long serialVersionUID = 1L;



  /**
   * // * Default constructor.
   */
  public SmartCampusException() {
    super();
  }

  /**
   * Constructor with message and cause.
   * 
   * @param message the message of the exceptin
   * @param cause the cause of the exception
   */
  public SmartCampusException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructor with message.
   * 
   * @param message the message of the exception
   */
  public SmartCampusException(String message) {
    super(message);
  }

  /**
   * Constructor with cause.
   * 
   * @param cause the cause of the exception
   */
  public SmartCampusException(Throwable cause) {
    super(cause);
  }



}

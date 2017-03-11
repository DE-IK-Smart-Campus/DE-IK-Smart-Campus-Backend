package hu.unideb.smartcampus.service.api;

/**
 * An exception class to indicate that a summary does not matches a given pattern.
 *
 */
public class UnparsableCalendarEventSummaryException extends Exception {

  /**
   * Default constructor.
   */
  public UnparsableCalendarEventSummaryException() {
    super();
  }

  /**
   * Constructor with message and cause parameters.
   * @param message the message of the exception
   * @param cause the cause of the exception
   */
  public UnparsableCalendarEventSummaryException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructor with message parameter.
   * 
   * @param message the message of the exception
   */
  public UnparsableCalendarEventSummaryException(String message) {
    super(message);
  }

  /**
   * Constructor with cause parameter.
   * 
   * @param cause the cause of the exception
   */
  public UnparsableCalendarEventSummaryException(Throwable cause) {
    super(cause);
  }

}

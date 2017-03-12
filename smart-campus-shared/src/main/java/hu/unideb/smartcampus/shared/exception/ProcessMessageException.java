package hu.unideb.smartcampus.shared.exception;

/**
 * ProcessMessageException which is thrown by MessageProcessService.
 *
 */
public class ProcessMessageException extends Exception {

  private static final long serialVersionUID = -3664592362931581825L;

  /**
   * Constructs an empty ProcessMessageException.
   */
  public ProcessMessageException() {
    super();
  }

  /**
   * Constructs a ProcessMessageException with message.
   */
  public ProcessMessageException(String message) {
    super(message);
  }

  /**
   * Constructs a ProcessMessageException with message and cause.
   */
  public ProcessMessageException(String message, Throwable cause) {
    super(message, cause);
  }

}

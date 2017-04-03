package hu.unideb.smartcampus.shared.exception;

public class InvalidXmppConnectionException extends SmartCampusException {

  public InvalidXmppConnectionException() {
    super();
  }

  public InvalidXmppConnectionException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidXmppConnectionException(String message) {
    super(message);
  }

  public InvalidXmppConnectionException(Throwable cause) {
    super(cause);
  }

}
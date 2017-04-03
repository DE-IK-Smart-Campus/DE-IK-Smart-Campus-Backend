package hu.unideb.smartcampus.shared.exception;

public class UserLocationSendFailedException extends SmartCampusException {

  public UserLocationSendFailedException() {
    super();
  }

  public UserLocationSendFailedException(String message, Throwable cause) {
    super(message, cause);
  }

  public UserLocationSendFailedException(String message) {
    super(message);
  }

  public UserLocationSendFailedException(Throwable cause) {
    super(cause);
  }

}

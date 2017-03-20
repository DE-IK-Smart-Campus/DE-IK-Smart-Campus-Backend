package hu.unideb.smartcampus.service.api.exception;


public class UserNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 578774199273368238L;

  public UserNotFoundException(final String message) {
    super(message);
  }
}

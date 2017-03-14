package hu.unideb.smartcampus.web.config.security;

/**
 * Thrown if the full login process is failed at a point.
 */
public class SmartCampusFullLoginFailedException extends RuntimeException {

  /**
   * Constructor with cause parameter.
   * 
   * @param cause the cause of the exception 
   */
  public SmartCampusFullLoginFailedException(Throwable cause) {
    super(cause);
  }



}

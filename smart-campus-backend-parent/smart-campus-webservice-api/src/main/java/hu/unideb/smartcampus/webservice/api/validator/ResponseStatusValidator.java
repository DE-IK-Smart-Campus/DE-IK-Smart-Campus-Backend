package hu.unideb.smartcampus.webservice.api.validator;

import javax.ws.rs.core.Response;

/**
 * Service for checking response status.
 *
 */
public interface ResponseStatusValidator {

  /**
   * Checks if a response's status is OK.
   *
   * @param response given response.
   * @return true if status code is 200, otherwise false.
   */
  boolean isOk(Response response);
}

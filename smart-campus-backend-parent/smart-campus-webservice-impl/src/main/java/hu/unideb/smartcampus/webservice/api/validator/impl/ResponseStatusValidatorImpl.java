package hu.unideb.smartcampus.webservice.api.validator.impl;

import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import hu.unideb.smartcampus.webservice.api.validator.ResponseStatusValidator;

/**
 * ResponseStatusValidator implementation.
 */
@Service
public class ResponseStatusValidatorImpl implements ResponseStatusValidator {

  /**
   * {@inheritDoc}.
   */
  @Override
  public boolean isOk(Response response) {
    return Response.Status.OK.equals(response.getStatusInfo());
  }
}

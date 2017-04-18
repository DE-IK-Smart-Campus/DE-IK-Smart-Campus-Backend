package hu.unideb.smartcampus.webservice.api.validator.impl;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

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

  /**
   * {@inheritDoc}.
   */
  @Override
  public boolean isBadRequest(Response response) {
    return Response.Status.BAD_REQUEST.equals(response.getStatusInfo());
  }
}

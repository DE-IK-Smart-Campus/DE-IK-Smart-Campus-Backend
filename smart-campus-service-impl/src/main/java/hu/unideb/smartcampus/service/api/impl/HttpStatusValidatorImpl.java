package hu.unideb.smartcampus.service.api.impl;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.HttpStatusValidator;

/**
 * HttpStatusValidator implementation.
 *
 */
@Service
public class HttpStatusValidatorImpl implements HttpStatusValidator {

  /**
   * {@inheritDoc}.
   */
  @Override
  public boolean isOk(Response response) {
    return Response.Status.OK.equals(response.getStatusInfo());
  }

}

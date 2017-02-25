package hu.unideb.smartcampus.service.api.provider;

import javax.ws.rs.core.Response;
import hu.unideb.smartcampus.service.ejabberd.sharedroster.request.BaseRequest;

/**
 * Client response provider.
 */
public interface ClientResponseProvider {

  /**
   * Send post request to the endpoint.
   */
  <T extends BaseRequest> Response sendPostRequest(String endpoint, T request);
}

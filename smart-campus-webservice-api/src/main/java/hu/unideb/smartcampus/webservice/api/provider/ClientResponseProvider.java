package hu.unideb.smartcampus.webservice.api.provider;

import javax.ws.rs.core.Response;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.sharedroster.BaseRequest;


/**
 * Client response provider.
 */
public interface ClientResponseProvider {

  /**
   * Send post request to the endpoint.
   */
  <T extends BaseRequest> Response sendPostRequest(String endpoint, T request);
}

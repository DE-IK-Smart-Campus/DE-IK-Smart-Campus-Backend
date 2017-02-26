package hu.unideb.smartcampus.webservice.api.provider;

import javax.ws.rs.client.WebTarget;

/**
 * Provides a WebTarget.
 *
 */
public interface ClientProvider {

  /**
   * Constructs a WebTarget instance with given URL.
   *
   * @param url url to be called with webtarget.
   * @return webtarget instance with URL.
   */
  WebTarget createClientByUrl(String url);
}

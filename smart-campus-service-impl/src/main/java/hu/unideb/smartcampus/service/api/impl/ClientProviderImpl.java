package hu.unideb.smartcampus.service.api.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.ClientProvider;

/**
 * ClientProvider implementation.
 *
 */
@Service
public class ClientProviderImpl implements ClientProvider {

  private static final String SLASH = "/";
  private static final String COLON = ":";

  /**
   * Ejabberd REST API host.
   */
  @Value("${smartcampus.ejabberd.api.host}")
  private String host;

  /**
   * Ejabberd REST API port.
   */
  @Value("${smartcampus.ejabberd.api.port}")
  private Integer port;

  /**
   * Ejabberd REST API endpoint.
   */
  @Value("${smartcampus.ejabberd.api.endpoint}")
  private String endpoint;

  /**
   * {@inheritDoc}.
   */
  @Override
  public WebTarget createClientByUrl(String url) {
    Client client = ClientBuilder.newClient();
    return client.target(buildUrl() + url);
  }

  private String buildUrl() {
    return host + COLON + port + SLASH + endpoint;
  }
}

package hu.unideb.smartcampus.webservice.api.neptun;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;

import lombok.Builder;
import lombok.Data;

/**
 * Authenticator request filter for Neptun service.
 */
@Data
public class Authenticator implements ClientRequestFilter {

  /**
   * Auth type.
   */
  private static final String AUTH_TYPE = "Bearer ";

  /**
   * Token.
   */
  private final String token;

  /**
   * Builder.
   */
  @Builder
  public Authenticator(final String token) {
    this.token = token;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void filter(ClientRequestContext requestContext) throws IOException {
    MultivaluedMap<String, Object> headers = requestContext.getHeaders();
    final String basicAuthentication = getToken();
    headers.add("Authorization", basicAuthentication);

  }

  private String getToken() {
    return AUTH_TYPE + token;
  }
}

package hu.unideb.smartcampus.service.api.security;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedRequestBuilder {

  private static final String AUTH_HEADER_NAME = "Authorization";

  private static final String AUTH_PREFIX = "Basic ";

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticatedRequestBuilder.class);

  public Invocation.Builder buildRequestWithBasicAuthentication(final String url,
      final String username, final String password) {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url);
    return target.request(MediaType.APPLICATION_JSON).header(AUTH_HEADER_NAME,
        AUTH_PREFIX + encodePassword(username, password));
  }


  private String encodePassword(final String username, final String password) {
    String token = username + ":" + password;
    try {
      return DatatypeConverter.printBase64Binary(token.getBytes("UTF-8"));
    } catch (UnsupportedEncodingException e) {
      LOGGER.error("Failed to encode password!");
      return null;
    }
  }

}

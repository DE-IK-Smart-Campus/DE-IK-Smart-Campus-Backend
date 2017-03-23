package hu.unideb.smartcampus.service.api.security;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.authentication.AuthenticationCredentialProvider;
import hu.unideb.smartcampus.service.api.authentication.SecurityUrlProvider;
import hu.unideb.smartcampus.service.api.authentication.SmartCampusAuthentication;
import hu.unideb.smartcampus.shared.security.SmartCampusCredentials;

@Service
public class AuthenticationCredentialProviderImpl implements AuthenticationCredentialProvider {

  @Autowired
  private SecurityUrlProvider securityUrlProvider;

  @Autowired
  private AuthenticatedRequestBuilder authenticatedRequestBuilder;

  @Override
  public SmartCampusAuthentication getAuthentication(String username, String password) {
    final String url = securityUrlProvider.produceUrl();

    Response response = authenticatedRequestBuilder
        .buildRequestWithBasicAuthentication(url, username, password).get();

    if (response.getStatus() != Response.Status.OK.getStatusCode()) {
      return null;
      
    } else {
      
      SmartCampusCredentials credentials = response.readEntity(SmartCampusCredentials.class);
      return SmartCampusAuthentication.builder().username(credentials.getUsername())
          .roles(credentials.getRoles()).xmppPassword(credentials.getXmppPassword()).build();
      
    }
  }


}

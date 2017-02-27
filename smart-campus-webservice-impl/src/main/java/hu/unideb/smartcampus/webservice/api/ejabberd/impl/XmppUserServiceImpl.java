package hu.unideb.smartcampus.webservice.api.ejabberd.impl;

import static hu.unideb.smartcampus.shared.security.SecurityConstants.REGISTER_USER;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.shared.exception.XmppException;
import hu.unideb.smartcampus.webservice.api.ejabberd.XmppUserService;
import hu.unideb.smartcampus.webservice.api.ejabberd.domain.EjabberdUserRegistrationRequest;
import hu.unideb.smartcampus.webservice.api.provider.ClientProvider;

@Service
public class XmppUserServiceImpl implements XmppUserService {

  @Autowired
  private ClientProvider clientProvider;

  @Override
  public void createUser(String username, String password) throws XmppException {
    WebTarget target = clientProvider.createClientByUrl(REGISTER_USER);

    EjabberdUserRegistrationRequest registrationRequest = EjabberdUserRegistrationRequest.builder()
        .user(username).password(password).host("smartcampus").build();

    Response response = target.request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(registrationRequest, MediaType.APPLICATION_JSON));

    if (response.getStatus() != Response.Status.OK.getStatusCode()) {
      throw new XmppException("XMPP server responded with status " + response.getStatus());
    }

  }

}

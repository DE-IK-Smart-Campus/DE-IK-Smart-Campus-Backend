package hu.unideb.smartcampus.webservice.api.ejabberd.impl;

import static hu.unideb.smartcampus.shared.security.SecurityConstants.REGISTER_USER;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.shared.enumeration.ConfigPropertyKey;
import hu.unideb.smartcampus.shared.exception.XmppException;
import hu.unideb.smartcampus.webservice.api.ejabberd.XmppUserService;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.domain.EjabberdUserRegistrationRequest;
import hu.unideb.smartcampus.webservice.api.provider.ClientResponseProvider;
import hu.unideb.smartcampus.webservice.api.provider.PropertyProvider;
import hu.unideb.smartcampus.webservice.api.validator.ResponseStatusValidator;

@Service
public class XmppUserServiceImpl implements XmppUserService {

  @Autowired
  private ClientResponseProvider clientResponseProvider;

  @Autowired
  private PropertyProvider propertyProvider;

  @Autowired
  private ResponseStatusValidator responseStatusValidator;

  @Override
  public void createUser(String username, String password) throws XmppException {
    EjabberdUserRegistrationRequest registrationRequest = EjabberdUserRegistrationRequest.builder()
        .user(username).password(password).host(getXmppHost()).build();

    Response response = clientResponseProvider.sendPostRequest(REGISTER_USER, registrationRequest);

    if (!responseStatusValidator.isOk(response)) {
      throw new XmppException("XMPP server responded with status " + response.getStatus());
    }

  }

  private String getXmppHost() {
    return propertyProvider.getPropertyValue(ConfigPropertyKey.SMART_CAMPUS_XMPP_HOST);
  }

}

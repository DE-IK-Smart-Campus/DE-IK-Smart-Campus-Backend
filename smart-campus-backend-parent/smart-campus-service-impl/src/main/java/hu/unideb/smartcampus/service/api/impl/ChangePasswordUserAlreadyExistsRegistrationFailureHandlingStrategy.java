package hu.unideb.smartcampus.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.UserAlreadyExistsRegistrationFailureHandlingStrategy;
import hu.unideb.smartcampus.shared.exception.SmartCampusException;
import hu.unideb.smartcampus.webservice.api.ejabberd.XmppUserService;

@Service
public class ChangePasswordUserAlreadyExistsRegistrationFailureHandlingStrategy
    implements UserAlreadyExistsRegistrationFailureHandlingStrategy {

  @Autowired
  private XmppUserService xmppUserService;

  @Override
  public void handle(String username, String password) throws SmartCampusException {
    xmppUserService.changePassword(username, password);
  }

  @Override
  public boolean failRegistration() {
    return false;
  }



}

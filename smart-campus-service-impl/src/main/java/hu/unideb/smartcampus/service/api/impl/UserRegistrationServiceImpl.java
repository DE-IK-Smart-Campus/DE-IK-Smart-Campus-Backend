package hu.unideb.smartcampus.service.api.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import hu.unideb.smartcampus.service.api.UserRegistrationService;
import hu.unideb.smartcampus.service.api.UserService;
import hu.unideb.smartcampus.service.api.domain.User;
import hu.unideb.smartcampus.service.api.exception.RegistrationFailedException;
import hu.unideb.smartcampus.shared.enumeration.Role;
import hu.unideb.smartcampus.shared.exception.XmppException;
import hu.unideb.smartcampus.webservice.api.ejabberd.XmppUserService;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

  @Autowired
  private XmppUserService xmppUserService;

  @Autowired
  private UserService userService;

  @Override
  @Transactional(rollbackFor = RegistrationFailedException.class,
      propagation = Propagation.REQUIRES_NEW)
  public void doRegister(String username) throws RegistrationFailedException {
    Assert.notNull(username);

    final String generatedPassword = UUID.randomUUID().toString();

    try {
      xmppUserService.createUser(username, generatedPassword);
    } catch (XmppException e) {
      throw new RegistrationFailedException(e);
    }

    User user = User.builder().username(username).password(generatedPassword).role(Role.USER).build();

    userService.save(user);
  }

}

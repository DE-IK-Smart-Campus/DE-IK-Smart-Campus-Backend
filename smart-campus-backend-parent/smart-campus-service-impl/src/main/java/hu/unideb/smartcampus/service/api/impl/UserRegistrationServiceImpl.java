package hu.unideb.smartcampus.service.api.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import hu.unideb.smartcampus.service.api.UserAlreadyExistsRegistrationFailureHandlingStrategy;
import hu.unideb.smartcampus.service.api.UserRegistrationService;
import hu.unideb.smartcampus.service.api.UserService;
import hu.unideb.smartcampus.service.api.domain.User;
import hu.unideb.smartcampus.shared.enumeration.Role;
import hu.unideb.smartcampus.shared.exception.RegistrationFailedException;
import hu.unideb.smartcampus.shared.exception.SmartCampusException;
import hu.unideb.smartcampus.webservice.api.ejabberd.XmppUserService;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

  @Autowired
  private XmppUserService xmppUserService;

  @Autowired
  private UserService userService;

  @Autowired
  private UserAlreadyExistsRegistrationFailureHandlingStrategy registrationFailureHandlingStrategy;

  @Override
  @Transactional(rollbackFor = RegistrationFailedException.class,
      propagation = Propagation.REQUIRES_NEW)
  public void doRegister(String username) throws RegistrationFailedException {
    Assert.notNull(username);

    final String generatedPassword = UUID.randomUUID().toString();
    try {
      boolean userExists = xmppUserService.userExists(username);
      if (userExists) {
        registrationFailureHandlingStrategy.handle(username, generatedPassword);
        boolean fail = registrationFailureHandlingStrategy.failRegistration();
        if (fail) {
          throw new RegistrationFailedException("The user already exists");
        }
      } else {
        xmppUserService.createUser(username, generatedPassword);
      }

    } catch (SmartCampusException e) {
      throw new RegistrationFailedException(e);
    }

    User user =
        User.builder().username(username).password(generatedPassword).role(Role.USER).build();

    userService.save(user);
  }

}

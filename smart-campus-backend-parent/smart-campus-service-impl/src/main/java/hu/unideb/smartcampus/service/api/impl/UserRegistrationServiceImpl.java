package hu.unideb.smartcampus.service.api.impl;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import hu.unideb.smartcampus.webservice.api.neptun.NeptunEndpointService;
import hu.unideb.smartcampus.webservice.api.neptun.NeptunInfo;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);

  @Autowired
  private XmppUserService xmppUserService;

  @Autowired
  private UserService userService;

  @Autowired
  private UserAlreadyExistsRegistrationFailureHandlingStrategy registrationFailureHandlingStrategy;

  @Autowired
  private NeptunEndpointService neptunEndpointService;

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


    User user;
    try {
      user = createUser(username, generatedPassword);
      userService.save(user);
    } catch (IOException e) {
      throw new RegistrationFailedException("Failed to save user entity because of Neptun error.",
          e);
    }

  }

  private User createUser(String username, final String generatedPassword) throws IOException {
    NeptunInfo neptunInfo = neptunEndpointService.getNeptunInfoByUid(username);
    return User.builder()
        .username(username)
        .password(generatedPassword)
        .neptunIdentifier(neptunInfo.getMemberInfo().getNeptunIdentifier())
        .fullName(neptunInfo.getMemberInfo().getTeljnev())
        .role(Role.USER)
        .build();
  }

}

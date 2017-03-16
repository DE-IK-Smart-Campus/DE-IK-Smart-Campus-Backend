package hu.unideb.smartcampus.web.config.security;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

import hu.unideb.smartcampus.service.api.UserRegistrationService;
import hu.unideb.smartcampus.service.api.UserService;
import hu.unideb.smartcampus.service.api.domain.User;
import hu.unideb.smartcampus.service.api.exception.RegistrationFailedException;
import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.exception.XmppException;

/**
 * Synchronizes and logs in the user across all modules.
 *
 */
public class SmartCampusSynchronizingContextMapper extends LdapUserDetailsMapper {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SmartCampusSynchronizingContextMapper.class);

  @Autowired
  private UserRegistrationService userRegistrationService;

  @Autowired
  private UserService userService;

  @Autowired
  private EjabberdUser ejabberdUser;

  @Override
  public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
      Collection<? extends GrantedAuthority> authorities) {

    if (userRegistrationRequired(username)) {
      try {
        userRegistrationService.doRegister(username);
      } catch (RegistrationFailedException e) {
        LOGGER.error("Failed to registrate user", e);
        throw new SmartCampusFullLoginFailedException(e);
      }
    }

    final User user = userService.getByUsername(username).get();

    try {
      ejabberdUser.login(user.getUsername(), user.getPassword());
    } catch (XmppException e) {
      throw new SmartCampusFullLoginFailedException(e);
    }

    final UserDetails userDetails = super.mapUserFromContext(ctx, username, authorities);
    return new SmartCampusUserDetails(userDetails, user);
  }


  protected boolean userRegistrationRequired(String username) {
    Optional<User> user = userService.getByUsername(username);
    return !user.isPresent();
  }

}

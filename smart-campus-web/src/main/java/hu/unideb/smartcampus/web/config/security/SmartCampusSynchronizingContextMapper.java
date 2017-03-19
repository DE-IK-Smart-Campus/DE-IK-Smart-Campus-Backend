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

/**
 * Synchronizes and logs in the user across all modules.
 *
 */
public class SmartCampusSynchronizingContextMapper extends LdapUserDetailsMapper {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SmartCampusSynchronizingAuthenticationSuccessHandler.class);

  @Autowired
  private UserService userService;

  @Autowired
  private UserRegistrationService userRegistrationService;

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

    final UserDetails userDetails = super.mapUserFromContext(ctx, username, authorities);
    Optional<User> user = userService.getByUsername(username);
    return new SmartCampusUserDetails(userDetails, user.isPresent() ? user.get() : null);
  }


  protected boolean userRegistrationRequired(String username) {
    return !userService.getByUsername(username).isPresent();
  }

}

package hu.unideb.smartcampus.web.config.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.service.api.AsyncSubjectEventService;
import hu.unideb.smartcampus.service.api.UserRegistrationService;
import hu.unideb.smartcampus.service.api.UserService;
import hu.unideb.smartcampus.service.api.domain.User;
import hu.unideb.smartcampus.service.api.util.RoleUtil;
import hu.unideb.smartcampus.shared.exception.RegistrationFailedException;

/**
 * Synchronizes and logs in the user across all modules.
 *
 */
public class SmartCampusSynchronizingContextMapper extends LdapUserDetailsMapper {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SmartCampusSynchronizingContextMapper.class);

  @Autowired
  private UserService userService;

  @Autowired
  private UserRegistrationService userRegistrationService;

  @Autowired
  private AsyncSubjectEventService asyncSubjectEventService;

  @Autowired
  private RoleUtil roleUtil;

  @Transactional
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
    synchronizeUseTimeTableIfRequired(user);
    return new SmartCampusUserDetails(userDetails, user.isPresent() ? user.get() : null);
  }

  private void synchronizeUseTimeTableIfRequired(Optional<User> optionalUser) {
    try {
      if (optionalUser.isPresent()) {
        User user = optionalUser.get();
        if (roleUtil.isStudent(user)) {
          syncTimeTable(user);
        }
      }
    } catch (IOException e) {
      LOGGER.error("Error while syncing student time table, try again later.");
    }
  }

  protected boolean userRegistrationRequired(String username) {
    return !userService.getByUsername(username).isPresent();
  }

  private void syncTimeTable(User user) throws IOException {
    LOGGER.info("Synching user subjects.");
    String neptunIdentifier = user.getNeptunIdentifier();
    String username = user.getUsername();
    asyncSubjectEventService.saveSubjectEventsAsync(neptunIdentifier, username);
  }

}

package hu.unideb.smartcampus.web.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

/**
 * Synchronizes and logs in the user across all modules.
 *
 */
public class SmartCampusSynchronizingContextMapper extends LdapUserDetailsMapper {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SmartCampusSynchronizingContextMapper.class);

  // @Autowired
  // private UserService userService;
  //
  // @Autowired
  // private UserRegistrationService userRegistrationService;
  //
  // @Override
  // public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
  // Collection<? extends GrantedAuthority> authorities) {
  //
  // if (userRegistrationRequired(username)) {
  // try {
  // userRegistrationService.doRegister(username);
  // } catch (RegistrationFailedException e) {
  // LOGGER.error("Failed to registrate user", e);
  // throw new SmartCampusFullLoginFailedException(e);
  // }
  // }
  //
  // final UserDetails userDetails = super.mapUserFromContext(ctx, username, authorities);
  // Optional<User> user = userService.getByUsername(username);
  // return new SmartCampusUserDetails(userDetails, user.isPresent() ? user.get() : null);
  // }
  //
  //
  // protected boolean userRegistrationRequired(String username) {
  // return !userService.getByUsername(username).isPresent();
  // }

}

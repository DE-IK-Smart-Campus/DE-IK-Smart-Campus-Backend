package hu.unideb.smartcampus.web.restcontroller;

import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.web.config.security.SmartCampusUserDetails;
import hu.unideb.smartcampus.web.domain.PublicUser;

/**
 * The container of the integratino methods for the authentication of a user and for the handling of
 * the user's credentials.
 *
 */
@RestController
@RequestMapping(path = "/integration")
@PreAuthorize("isAuthenticated()")
public class IntegrationCredentialProviderRestController {

  /**
   * Retrieves the credential of the user currently logged in.
   * 
   * @return the credentials of the user
   */
  @GetMapping(path = "/retrieveUserData", produces = MediaType.APPLICATION_JSON_VALUE)
  public PublicUser retrieveUserFromContext() {
    SmartCampusUserDetails auth = retrieveAuthentication();
    return convertToPublic(auth);
  }

  private PublicUser convertToPublic(SmartCampusUserDetails auth) {
    return PublicUser.builder().roles(auth.getAuthorities().stream()
        .map((GrantedAuthority authority) -> authority.getAuthority()).collect(Collectors.toList()))
        .username(auth.getUsername()).xmppPassword(auth.getUser().getPassword()).build();
  }

  private SmartCampusUserDetails retrieveAuthentication() {
    return (SmartCampusUserDetails) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
  }
}

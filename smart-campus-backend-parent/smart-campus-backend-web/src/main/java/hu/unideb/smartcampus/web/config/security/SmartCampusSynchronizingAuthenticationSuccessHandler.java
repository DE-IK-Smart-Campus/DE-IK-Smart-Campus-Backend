package hu.unideb.smartcampus.web.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import hu.unideb.smartcampus.service.api.domain.User;
import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.exception.XmppException;

/**
 * Synchronizes the user with the ldap server on the first authentication.
 *
 */
public class SmartCampusSynchronizingAuthenticationSuccessHandler
    implements AuthenticationSuccessHandler {

  @Autowired
  private EjabberdUser ejabberdUser;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    final User user = ((SmartCampusUserDetails) authentication.getPrincipal()).getUser();
    try {
      ejabberdUser.login(user.getUsername(), user.getPassword());
    } catch (XmppException e) {
      throw new SmartCampusFullLoginFailedException(e);
    }

  }

}

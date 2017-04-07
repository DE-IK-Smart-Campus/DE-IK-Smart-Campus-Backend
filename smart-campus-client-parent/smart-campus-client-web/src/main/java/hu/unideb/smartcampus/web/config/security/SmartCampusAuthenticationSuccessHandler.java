package hu.unideb.smartcampus.web.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.unideb.smartcampus.service.api.authentication.SmartCampusUserDetails;
import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.exception.XmppException;

public class SmartCampusAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SmartCampusAuthenticationSuccessHandler.class);

  private static final String REDIRECT_TO_DASHBOARD = "dashboard";

  @Autowired
  private EjabberdUser ejabberdUser;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    final SmartCampusUserDetails user = getUserFromAuthentication(authentication);
    try {
      ejabberdUser.login(user.getUsername(), user.getPassword());
      response.sendRedirect(REDIRECT_TO_DASHBOARD);
    } catch (XmppException e) {
      LOGGER.error("Failed to log in at XMPP", e);
    }
  }

  private SmartCampusUserDetails getUserFromAuthentication(Authentication authentication) {
    return ((SmartCampusUserDetails) authentication.getPrincipal());
  }

}

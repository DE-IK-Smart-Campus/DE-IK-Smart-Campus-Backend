package hu.unideb.smartcampus.web.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;

/**
 * Logs out the user from ejabberd service.
 *
 */
public class SmartCampusLogoutSuccessHandler implements LogoutSuccessHandler {

  @Autowired
  private EjabberdUser ejabberdUser;

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    ejabberdUser.logout();

  }

}

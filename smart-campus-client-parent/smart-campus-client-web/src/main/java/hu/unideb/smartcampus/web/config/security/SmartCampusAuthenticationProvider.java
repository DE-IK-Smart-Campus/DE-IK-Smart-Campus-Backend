package hu.unideb.smartcampus.web.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import hu.unideb.smartcampus.service.api.authentication.AuthenticationCredentialProvider;
import hu.unideb.smartcampus.service.api.authentication.SmartCampusAuthentication;
import hu.unideb.smartcampus.service.api.authentication.SmartCampusUserDetails;

public class SmartCampusAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

  @Autowired
  private AuthenticationCredentialProvider authenticationCredentialProvider;

  @Override
  protected void additionalAuthenticationChecks(UserDetails userDetails,
      UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

  }

  @Override
  protected UserDetails retrieveUser(String username,
      UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    UsernamePasswordAuthenticationToken token =
        (UsernamePasswordAuthenticationToken) authentication;
    String password = (String) token.getCredentials();

    SmartCampusAuthentication authenticationData =
        authenticationCredentialProvider.getAuthentication(username, password);
    
    if (authenticationData == null) {
      throw new UsernameNotFoundException("Authentication failed");
    }
    
    List<GrantedAuthority> authorities = mapAuthorities(authenticationData.getRoles());

    return new SmartCampusUserDetails(authenticationData.getUsername(), authorities,
        authenticationData.getXmppPassword());
  }

  private GrantedAuthority mapAuthority(String role) {
    return new SimpleGrantedAuthority(role);
  }

  private List<GrantedAuthority> mapAuthorities(List<String> roles) {
    List<GrantedAuthority> result = new ArrayList<>();

    for (String role : roles) {
      result.add(mapAuthority(role));
    }
    return result;
  }

}

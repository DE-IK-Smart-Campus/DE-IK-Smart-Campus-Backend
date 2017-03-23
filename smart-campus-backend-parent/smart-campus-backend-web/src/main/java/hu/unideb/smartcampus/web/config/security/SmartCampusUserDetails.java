package hu.unideb.smartcampus.web.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import hu.unideb.smartcampus.service.api.domain.User;

public class SmartCampusUserDetails implements UserDetails {

  private UserDetails userDetails;

  private User user;

  public SmartCampusUserDetails(UserDetails userDetails, User user) {
    this.userDetails = userDetails;
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return userDetails.getAuthorities();
  }

  @Override
  public String getPassword() {
    return userDetails.getPassword();
  }

  @Override
  public String getUsername() {
    return userDetails.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return userDetails.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return userDetails.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return userDetails.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return userDetails.isEnabled();
  }

  /**
   * Returns the persisted User in the database.
   * 
   * @return the user persisted logged in
   */
  public User getUser() {
    return user;
  }


}

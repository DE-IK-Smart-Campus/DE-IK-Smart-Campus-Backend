package hu.unideb.smartcampus.web.config.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Data;

@Data
public class SmartCampusUserDetails implements UserDetails {

  private String username;

  private List<GrantedAuthority> roles;

  private String xmppPassword;

  @Builder
  public SmartCampusUserDetails(String username, List<GrantedAuthority> roles, String xmppPassword) {
    super();
    this.username = username;
    this.roles = roles;
    this.xmppPassword = xmppPassword;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }

  @Override
  public String getPassword() {
    return xmppPassword;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}

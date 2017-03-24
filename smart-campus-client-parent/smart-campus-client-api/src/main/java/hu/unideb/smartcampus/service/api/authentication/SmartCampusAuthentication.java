package hu.unideb.smartcampus.service.api.authentication;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SmartCampusAuthentication {

  private String username;

  private List<String> roles;

  private String xmppPassword;

  @Builder
  public SmartCampusAuthentication(String username, List<String> roles, String xmppPassword) {
    super();
    this.username = username;
    this.roles = roles;
    this.xmppPassword = xmppPassword;
  }
  
}
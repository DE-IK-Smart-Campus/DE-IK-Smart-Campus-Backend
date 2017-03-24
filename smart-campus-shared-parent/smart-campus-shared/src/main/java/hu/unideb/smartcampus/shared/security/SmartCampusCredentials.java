package hu.unideb.smartcampus.shared.security;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SmartCampusCredentials {

  private String username;

  private List<String> roles;

  private String xmppPassword;

  @Builder
  public SmartCampusCredentials(String username, List<String> roles, String xmppPassword) {
    super();
    this.username = username;
    this.roles = roles;
    this.xmppPassword = xmppPassword;
  }

}

package hu.unideb.smartcampus.web.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


/**
 * A public POJO to provide information of the user logged in.
 *
 */
@Data
@ToString(exclude = "xmppPassword")
public class PublicUser {

  /**
   * The username of the user.
   */
  private String username;

  /**
   * The password of the user at the XMPP server.
   */
  private String xmppPassword;

  /**
   * The roles of the user.
   */
  private List<String> roles;

  /**
   * All arguments constructor.
   * 
   * @param username the username of the user
   * @param xmppPassword the password of the user at the XMPP server
   * @param roles the roles of the user
   */
  @Builder
  public PublicUser(String username, String xmppPassword, List<String> roles) {
    super();
    this.username = username;
    this.xmppPassword = xmppPassword;
    this.roles = roles;
  }

}

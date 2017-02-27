package hu.unideb.smartcampus.webservice.api.ejabberd.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * A representation of the Ejabberd XMPP server's user creation API call.
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EjabberdUserRegistrationRequest {
  
  /**
   * The username of the created user.
   */
  private String user;

  /**
   * The password of the created user.
   */
  private String password;
  
  /**
   * The host of the created user.
   */
  private String host;

}

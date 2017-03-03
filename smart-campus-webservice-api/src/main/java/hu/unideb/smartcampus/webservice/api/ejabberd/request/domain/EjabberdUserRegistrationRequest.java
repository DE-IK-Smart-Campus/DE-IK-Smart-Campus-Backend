package hu.unideb.smartcampus.webservice.api.ejabberd.request.domain;

import hu.unideb.smartcampus.webservice.api.ejabberd.request.base.BaseRequest;
import lombok.Builder;
import lombok.Data;


/**
 * A representation of the Ejabberd XMPP server's user creation API call.
 *
 */
@Data
public class EjabberdUserRegistrationRequest extends BaseRequest {
  
  /**
   * Serial version Id.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The username of the created user.
   */
  private String user;

  /**
   * The password of the created user.
   */
  private String password;
  
  /**
   * Creates an instance of a registration request.
   * 
   * @param host the host of the XMPP server 
   * @param user the username of the user
   * @param password the password of the users
   */
  @Builder
  public EjabberdUserRegistrationRequest(String host, String user, String password) {
    super(host);
    this.user = user;
    this.password = password;
  }

  
  
}

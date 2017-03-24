package hu.unideb.smartcampus.webservice.api.ejabberd.request.domain;

import hu.unideb.smartcampus.webservice.api.ejabberd.request.base.BaseRequest;
import lombok.Builder;
import lombok.Data;

/**
 * Request domain fro changing the password of a user.
 *
 */
@Data
public class EjabberdChangePasswordRequest extends BaseRequest {

  /**
   * The user of the change password request.
   */
  private String user;

  /**
   * The new password of the user.
   */
  private String newpass;

  /**
   * All arguments constructor.
   * 
   * @param host the host of the user
   * @param user the username of the user
   * @param newpass the new password of the user
   */
  @Builder
  public EjabberdChangePasswordRequest(String host, String user, String newpass) {
    super(host);
    this.user = user;
    this.newpass = newpass;
  }



}

package hu.unideb.smartcampus.webservice.api.ejabberd.request.domain;

import hu.unideb.smartcampus.webservice.api.ejabberd.request.base.BaseRequest;
import lombok.Builder;
import lombok.Data;


/**
 * Request domain for checking if a user already exists.
 *
 */
@Data
public class EjabberdAccountCheckRequest extends BaseRequest {

  /**
   * The username of the user.
   */
  private String user;

  /**
   * All arguments constructor.
   * 
   * @param host the host of the user
   * @param user the username of the user
   */
  @Builder
  public EjabberdAccountCheckRequest(String host, String user) {
    super(host);
    this.user = user;
  }

}

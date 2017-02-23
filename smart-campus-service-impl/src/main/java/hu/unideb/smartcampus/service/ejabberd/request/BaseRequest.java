package hu.unideb.smartcampus.service.ejabberd.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A parent class for every XMPP request.
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * The main host.
   */
  protected String host;

}

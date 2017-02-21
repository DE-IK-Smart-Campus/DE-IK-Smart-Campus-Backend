package hu.unideb.smartcampus.service.ejabberd.request;

import java.io.Serializable;

/**
 * A parent class for every XMPP request.
 *
 */
public class BaseRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * The main host.
   */
  protected String host;

  /**
   * Returns the host.
   *
   * @return the host.
   */
  public String getHost() {
    return host;
  }

  /**
   * Set the host.
   *
   * @param host the host.
   */
  public void setHost(String host) {
    this.host = host;
  }
}

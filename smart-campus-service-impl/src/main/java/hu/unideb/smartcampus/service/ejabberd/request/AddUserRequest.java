package hu.unideb.smartcampus.service.ejabberd.request;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Request which can be used to add user to a given group on a host.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings({"PMD.SingularField"})
public class AddUserRequest extends BaseRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Group where the user should be added.
   */
  private final String group;

  /**
   * JID of the username.
   */
  private final String user;

  /**
   * Host of the group, sometimes it is the XMPP server's host.
   */
  private final String grouphost;

  /**
   * Constructor which is made for the @Builder for Lombok.
   *
   * @param host the host.
   * @param group the group.
   * @param user the user.
   */
  @Builder
  public AddUserRequest(String host, String group, String user) {
    super(host);
    this.group = group;
    this.user = user;
    this.grouphost = host;
  }



}

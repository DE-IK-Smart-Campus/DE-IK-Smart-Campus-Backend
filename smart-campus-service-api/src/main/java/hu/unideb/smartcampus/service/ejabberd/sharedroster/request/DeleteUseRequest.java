package hu.unideb.smartcampus.service.ejabberd.sharedroster.request;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Delete request can be used to delete user from given group.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings({"PMD.SingularField"})
public class DeleteUseRequest extends BaseRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Group from the user should be removed.
   */
  private final String group;

  /**
   * JID of the user.
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
   * @param user the user's JID which should be deleted.
   */
  @Builder
  public DeleteUseRequest(String host, String group, String user) {
    super(host);
    this.group = group;
    this.user = user;
    this.grouphost = host;
  }



}

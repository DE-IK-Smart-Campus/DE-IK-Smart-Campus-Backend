package hu.unideb.smartcampus.webservice.api.ejabberd.request.muc;

import hu.unideb.smartcampus.webservice.api.ejabberd.request.sharedroster.BaseRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Subscribe request.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings({"PMD.SingularField"})
public class SubscribeRequest extends BaseRequest {

  /**
   * User's name.
   */
  private final String user;

  /**
   * User's nick.
   */
  private final String nick;

  /**
   * Room to subscribe.
   */
  private final String room;

  /**
   * Nodes.
   */
  private final String nodes;

  /**
   * Constructor which is made for the @Builder for Lombok.
   *
   * @param host host.
   * @param user username.
   * @param nick user's nick.
   * @param room room's name.
   * @param nodes nodes.
   */
  @Builder
  public SubscribeRequest(String host, String user, String nick, String room, String nodes) {
    super(host);
    this.user = user;
    this.nick = nick;
    this.room = room;
    this.nodes = nodes;
  }


}

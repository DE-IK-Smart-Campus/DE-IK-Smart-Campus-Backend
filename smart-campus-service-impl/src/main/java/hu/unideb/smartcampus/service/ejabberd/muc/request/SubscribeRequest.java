package hu.unideb.smartcampus.service.ejabberd.muc.request;

import lombok.Builder;
import lombok.Data;

/**
 * Subscribe request.
 */
@Data
@Builder
public class SubscribeRequest {

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
}

package hu.unideb.smartcampus.service.ejabberd.muc.request;

import lombok.Builder;
import lombok.Data;

/**
 * Unsubscribe request.
 */
@Data
@Builder
public class UnsubscribeRequest {

  /**
   * User's name.
   */
  private final String user;

  /**
   * Room name.
   */
  private final String room;
}

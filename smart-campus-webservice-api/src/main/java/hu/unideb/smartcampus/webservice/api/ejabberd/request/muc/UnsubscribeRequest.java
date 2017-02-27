package hu.unideb.smartcampus.webservice.api.ejabberd.request.muc;

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

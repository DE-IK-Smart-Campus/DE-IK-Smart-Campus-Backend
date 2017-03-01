package hu.unideb.smartcampus.webservice.api.ejabberd.request.muc;

import hu.unideb.smartcampus.webservice.api.ejabberd.request.sharedroster.BaseRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Unsubscribe request.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings({"PMD.SingularField"})
public class UnsubscribeRequest extends BaseRequest {

  /**
   * User's name.
   */
  private final String user;

  /**
   * Room name.
   */
  private final String room;

  /**
   * Constructor which is made for the @Builder for Lombok.
   *
   * @param host host.
   * @param user username.
   * @param room room's name.
   */
  @Builder
  public UnsubscribeRequest(String host, String user, String room) {
    super(host);
    this.user = user;
    this.room = room;
  }



}

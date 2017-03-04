package hu.unideb.smartcampus.webservice.api.ejabberd.request.muc;

import hu.unideb.smartcampus.webservice.api.ejabberd.request.base.BaseRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Destroy room request.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings({"PMD.SingularField"})
public class DestroyRoomRequest extends BaseRequest {

  /**
   * Room name.
   */
  private final String name;

  /**
   * Service name.
   */
  private final String service;

  /**
   * Constructor which is made for the @Builder for Lombok.
   *
   * @param host host.
   * @param name room name.
   * @param service service name.
   */
  @Builder
  public DestroyRoomRequest(String host, String name, String service) {
    super(host);
    this.name = name;
    this.service = service;
  }


}

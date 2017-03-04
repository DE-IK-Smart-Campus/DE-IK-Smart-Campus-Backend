package hu.unideb.smartcampus.webservice.api.ejabberd.request.muc;

import java.util.Map;

import hu.unideb.smartcampus.webservice.api.ejabberd.request.base.BaseRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Create room request.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings({"PMD.SingularField"})
public class CreateRoomRequest extends BaseRequest {

  /**
   * Room name.
   */
  private final String name;

  /**
   * Service name.
   */
  private final String service;

  /**
   * Room options.
   */
  private final Map<String, String> options;

  /**
   * Constructor which is made for the @Builder for Lombok.
   *
   * @param host the host.
   * @param name room name.
   * @param service service name.
   * @param options host.
   */
  @Builder
  public CreateRoomRequest(String host, String name, String service, Map<String, String> options) {
    super(host);
    this.name = name;
    this.service = service;
    this.options = options;
  }


}

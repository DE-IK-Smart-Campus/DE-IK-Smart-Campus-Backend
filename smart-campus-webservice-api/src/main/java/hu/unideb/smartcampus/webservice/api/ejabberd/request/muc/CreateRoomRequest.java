package hu.unideb.smartcampus.webservice.api.ejabberd.request.muc;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

/**
 * Create room request.
 *
 */
@Data
@Builder
public class CreateRoomRequest {

  /**
   * Room name.
   */
  private final String name;

  /**
   * Service name.
   */
  private final String service;

  /**
   * Host.
   */
  private final String host;

  /**
   * Room options.
   */
  private final Map<String, String> options;
}

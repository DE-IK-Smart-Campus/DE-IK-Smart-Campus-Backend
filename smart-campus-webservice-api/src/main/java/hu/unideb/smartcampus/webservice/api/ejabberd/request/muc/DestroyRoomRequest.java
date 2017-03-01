package hu.unideb.smartcampus.webservice.api.ejabberd.request.muc;

import lombok.Builder;
import lombok.Data;

/**
 * Destroy room request.
 *
 */
@Data
@Builder
public class DestroyRoomRequest {

  /**
   * Room name.
   */
  private final String name;

  /**
   * Service name.
   */
  private final String service;
}

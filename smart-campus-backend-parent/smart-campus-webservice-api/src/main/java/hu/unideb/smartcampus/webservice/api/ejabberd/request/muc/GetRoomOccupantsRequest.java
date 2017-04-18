package hu.unideb.smartcampus.webservice.api.ejabberd.request.muc;

import hu.unideb.smartcampus.webservice.api.ejabberd.request.base.BaseRequest;
import lombok.Builder;
import lombok.Data;

/**
 * Request for get room occupants.
 */
@Data
public class GetRoomOccupantsRequest extends BaseRequest{

  /**
   * 
   */
  private static final long serialVersionUID = 7151225351343568998L;

  /**
   * Name of the room.
   */
  private final String name;

  /**
   * MUC service.
   */
  private final String service;

  /**
   * Builder.
   */
  @Builder
  public GetRoomOccupantsRequest(String host, String name, String service) {
    super(host);
    this.name = name;
    this.service = service;
  }



}

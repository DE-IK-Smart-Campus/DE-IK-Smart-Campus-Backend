package hu.unideb.smartcampus.webservice.api.ejabberd.request.muc;

import java.util.List;

import hu.unideb.smartcampus.webservice.api.ejabberd.domain.MucMember;
import lombok.Builder;
import lombok.Data;

/**
 * Request for get room occupants.
 */
@Data
public class GetRoomOccupantsResponse {

  private final List<MucMember> members;
  
  private final boolean found;

  /**
   * Builder.
   */
  @Builder
  public GetRoomOccupantsResponse(List<MucMember> members, boolean found) {
    this.members = members;
    this.found = found;
  }
  
  
  
}

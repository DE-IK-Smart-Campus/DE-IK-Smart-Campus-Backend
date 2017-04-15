package hu.unideb.smartcampus.webservice.api.ejabberd;

import hu.unideb.smartcampus.webservice.api.ejabberd.request.muc.GetRoomOccupantsResponse;

/**
 * MUC generator service.
 */
public interface RoomGeneratorService {

  /**
   * Generate room with Smart Campus defined options.
   */
  void generateRoomWithOptions(String roomId, String roomName);
  
  /**
   * Get room occupants.
   */
  GetRoomOccupantsResponse getRoomOccupants(String room);
  
}

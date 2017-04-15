package hu.unideb.smartcampus.webservice.api.ejabberd.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.webservice.api.ejabberd.MultiUserChatService;
import hu.unideb.smartcampus.webservice.api.ejabberd.RoomGeneratorService;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.muc.GetRoomOccupantsResponse;

/**
 * MUC generator service impl.
 */
@Service
public class RoomGeneratorServiceImpl implements RoomGeneratorService {

  private static final String FALSE = Boolean.FALSE.toString();

  private static final String TRUE = Boolean.TRUE.toString();

  @Autowired
  private MultiUserChatService multiUserChatService;

  /**
   * {@inheritDoc}.
   */
  @Override
  public void generateRoomWithOptions(String roomId, String roomName) {
    Map<String, String> options = createOptions(roomName);
    multiUserChatService.createRoomWithOptions(roomId, options);
  }

  // Needed configuration, it make the room "private" and persistent too.
  private HashMap<String, String> createOptions(String roomName) {
    HashMap<String, String> options = new HashMap<>();
    options.put("public", FALSE);
    options.put("public_list", FALSE);
    options.put("mam", TRUE);
    options.put("persistent", TRUE);
    options.put("title", roomName);
    options.put("description", roomName);
    options.put("subject", roomName);
    return options;
  }

  @Override
  public GetRoomOccupantsResponse getRoomOccupants(String room) {
    return multiUserChatService.getRoomOccupants(room);
  }

}

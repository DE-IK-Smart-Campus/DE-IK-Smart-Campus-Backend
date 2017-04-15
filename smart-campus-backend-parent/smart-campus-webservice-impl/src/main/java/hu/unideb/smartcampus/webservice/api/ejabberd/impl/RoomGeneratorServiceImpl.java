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

  /**
   * Max users for each chat room, 200 should be enough!
   */
  private static final String MAX_USERS = "200";

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
    options.put("allow_change_subj", FALSE);
    options.put("allow_query_users", TRUE);
    options.put("allow_private_messages", FALSE);
    options.put("allow_private_messages_from_visitors", FALSE);
    options.put("allow_visitor_status", FALSE);
    options.put("allow_visitor_nickchange", FALSE);
    options.put("moderated", TRUE);
    options.put("members_by_default", TRUE);
    options.put("members_only", FALSE);
    options.put("allow_user_invites", FALSE);
    options.put("password_protected", FALSE);
    options.put("captcha_protected", FALSE);
    options.put("max_users", MAX_USERS);
    options.put("allow_voice_requests", FALSE);
    options.put("allow_subscription", FALSE);
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

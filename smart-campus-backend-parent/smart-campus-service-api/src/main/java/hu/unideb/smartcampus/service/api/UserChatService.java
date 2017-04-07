package hu.unideb.smartcampus.service.api;

import java.util.List;

/**
 * Service for listing and adding user chats and user muc rooms to user.
 */
public interface UserChatService {

  /**
   * Add partner JID to user.
   */
  void addChatToUser(String user, String partnerJid);

  /**
   * Add MUC room JID to user.
   */
  void addMucToUser(String user, String mucJid);

  /**
   * List user partner JIDs.
   */
  List<String> listUserChats(String user);

  /**
   * List user MUC room JIDs.
   */
  List<String> listUserMucRooms(String user);

}

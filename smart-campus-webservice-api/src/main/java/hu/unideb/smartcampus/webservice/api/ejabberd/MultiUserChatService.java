package hu.unideb.smartcampus.webservice.api.ejabberd;

import java.util.Map;

/**
 * Multi User Chat service.
 *
 */
public interface MultiUserChatService {

  /**
   * Create room with given name.
   *
   * @param roomName room name.
   */
  void createRoom(String roomName);

  /**
   * Subscribe user to room with given nickname.
   *
   * @param user username with room nick, example java/Java master.
   * @param nick nickname.
   * @param room room to subscribe.
   */
  void subscribeToRoom(String user, String nick, String room);

  /**
   * Unsubscribe user from room with given nickname.
   *
   * @param user user to unsubscribe.
   * @param room room from unsubsrcibe.
   */
  void unsubsribeFromRoom(String user, String room);

  /**
   * Create room with given room options, for example allow_subscription.
   *
   * @param roomName room name.
   * @param options options map.
   */
  void createRoomWithOptions(String roomName, Map<String, String> options);

  /**
   * Destroy room with given name.
   *
   * @param roomName room to be destroyed.
   */
  void destroyRoom(String roomName);


}

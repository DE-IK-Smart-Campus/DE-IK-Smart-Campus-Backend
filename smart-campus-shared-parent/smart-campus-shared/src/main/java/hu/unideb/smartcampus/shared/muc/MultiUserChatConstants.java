package hu.unideb.smartcampus.shared.muc;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Class to hold Ejabberd REST Endpoints for Multi User Chat API.
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MultiUserChatConstants {

  /**
   * Endpoint URL for creating new room.
   */
  public static final String MULTI_USER_CHAT_CREATE_ROOM_COMMAND = "/create_room";

  /**
   * Endpoint URL for subscribe user to room.
   */
  public static final String MULTI_USER_CHAT_SUBSCRIBE_COMMAND = "/subscribe_room";

  /**
   * Endpoint URL for unsubscribe user from chat room.
   */
  public static final String MULTI_USER_CHAT_UNSUBSCRIBE_COMMAND = "/unsubscribe_room";

  /**
   * Endpoint URL for creating new room with options.
   */
  public static final String MULTI_USER_CHAT_CREATE_ROOM_WITH_OPT_COMMAND =
      "/create_room_with_opts";

  /**
   * Endpoint URL for destroying room.
   */
  public static final String MULTI_USER_CHAT_DESTROY_ROOM_COMMAND = "/destroy_room";
}

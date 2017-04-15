package hu.unideb.smartcampus.service.api;

import java.util.List;

import hu.unideb.smartcampus.domain.MucRoom;

/**
 * Multi user chat service which return the rooms for the user.
 */
public interface MultiUserChatService {

  /**
   * Get logged in user MUC rooms.
   */
  List<MucRoom> getMucRooms();

}

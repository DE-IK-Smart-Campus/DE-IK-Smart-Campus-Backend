package hu.unideb.smartcampus.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.service.ejabberd.MultiUserChatService;

/**
 * MultiUserChatService endpoint.
 */
@RestController
public class MultiUserChatRestController {

  /**
   * MultiUserChatService dependency.
   */
  private final MultiUserChatService multiUserChatService;

  /**
   * Constructor for dependency injection.
   */
  @Autowired
  public MultiUserChatRestController(final MultiUserChatService multiUserChatService) {
    this.multiUserChatService = multiUserChatService;
  }

  /**
   * Get group list.
   *
   * @return {@link ResponseEntity}
   */
  @GetMapping(path = "/createRoom")
  public ResponseEntity createRoom(@RequestParam("roomname") String roomName) {
    multiUserChatService.createRoom(roomName);
    return ResponseEntity.ok("OK");
  }

  /**
   * Get group list.
   *
   * @return {@link ResponseEntity}
   */
  @GetMapping(path = "/subscribe")
  public ResponseEntity subscribeToRoom(@RequestParam("user") String user,
      @RequestParam("nick") String nick, @RequestParam("roomname") String roomName) {
    multiUserChatService.subscribeToRoom(user, nick, roomName);
    return ResponseEntity.ok("OK");
  }

  /**
   * Get group list.
   *
   * @return {@link ResponseEntity}
   */
  @GetMapping(path = "/unsubscribe")
  public ResponseEntity unsubscribeToRoom(@RequestParam("user") String user,
      @RequestParam("roomname") String roomName) {
    multiUserChatService.unsubsribeFromRoom(user, roomName);
    return ResponseEntity.ok("OK");
  }

  /**
   * Get group list.
   *
   * @return {@link ResponseEntity}
   */
  @GetMapping(path = "/destroyRoom")
  public ResponseEntity destroyRoom(@RequestParam("roomname") String roomName) {
    multiUserChatService.destroyRoom(roomName);
    return ResponseEntity.ok("OK");
  }
}

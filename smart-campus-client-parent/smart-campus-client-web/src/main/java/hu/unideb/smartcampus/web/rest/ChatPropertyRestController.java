package hu.unideb.smartcampus.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.domain.ChatProperties;
import hu.unideb.smartcampus.domain.MucRoom;
import hu.unideb.smartcampus.service.api.MultiUserChatService;
import hu.unideb.smartcampus.service.api.authentication.ChatPropertiesHolderService;

/**
 * Chat property REST controller.
 */
@RestController
@RequestMapping("/chat-properties")
public class ChatPropertyRestController {

  /**
   * Chat properties holder service.
   */
  @Autowired
  private ChatPropertiesHolderService chatPropertiesHolderService;

  @Autowired
  private MultiUserChatService multiUserChatService;

  /**
   * Get Converse JS connection properties.
   */
  @GetMapping
  public ChatProperties getChatProperties() {
    ChatProperties chatProperties = chatPropertiesHolderService.getChatProperties();
    List<MucRoom> mucRooms = multiUserChatService.getMucRooms();
    chatProperties.setMucRooms(mucRooms);
    return chatProperties;
  }

}

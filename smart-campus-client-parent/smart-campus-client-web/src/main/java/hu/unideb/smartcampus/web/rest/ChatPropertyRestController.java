package hu.unideb.smartcampus.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.service.api.authentication.ChatPropertiesHolderService;
import hu.unideb.smartcampus.domain.ChatProperties;

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

  /**
   * Get Converse JS connection properties.
   */
  @GetMapping
  public ChatProperties getChatProperties() {
    return chatPropertiesHolderService.getChatProperties();
  }

}

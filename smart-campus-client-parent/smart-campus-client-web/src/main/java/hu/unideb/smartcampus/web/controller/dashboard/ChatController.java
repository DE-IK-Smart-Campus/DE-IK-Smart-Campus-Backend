package hu.unideb.smartcampus.web.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO.
 */
@Controller
@RequestMapping(path = "/dashboard/chat")
public class ChatController {

  /**
   * TODO.
   */
  private static final String CHAT_VIEW = "dashboard/chat";

  /**
   * TODO.
   * @return asd
   */
  @GetMapping
  public String loadChatView() {
    return CHAT_VIEW;
  }
}


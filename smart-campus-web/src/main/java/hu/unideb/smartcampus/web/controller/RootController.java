package hu.unideb.smartcampus.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TODO.
 */
@Controller
public class RootController {

  /**
   * TODO.
   */
  private static final String REDIRECT_URL_TO_INDEX_PATH = "redirect:/index";

  /**
   * TODO.
   * @return asd
   */
  @GetMapping(path = {"", "/"})
  public String redirectToIndexPath() {
    return REDIRECT_URL_TO_INDEX_PATH;
  }
}

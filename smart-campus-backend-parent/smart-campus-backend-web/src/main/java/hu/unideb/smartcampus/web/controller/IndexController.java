package hu.unideb.smartcampus.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO.
 */
@Controller
@RequestMapping(path = "/index")
public class IndexController {

  /**
   * TODO.
   */
  private static final String INDEX_VIEW = "index";

  /**
   * TODO.
   * @return asd
   */
  @GetMapping
  public String loadIndexView() {
    return INDEX_VIEW;
  }
}

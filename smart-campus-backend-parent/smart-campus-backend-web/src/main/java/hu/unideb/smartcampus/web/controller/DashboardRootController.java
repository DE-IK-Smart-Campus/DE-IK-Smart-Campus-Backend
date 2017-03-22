package hu.unideb.smartcampus.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO.
 */
@Controller
@RequestMapping(path = "/dashboard")
public class DashboardRootController {

  /**
   * TODO.
   */
  private static final String REDIRECT_URL_TO_HOME_PATH = "redirect:/dashboard/home";

  /**
   * TODO.
   * @return asd
   */
  @GetMapping
  public String redirectToHomePath() {
    return REDIRECT_URL_TO_HOME_PATH;
  }
}
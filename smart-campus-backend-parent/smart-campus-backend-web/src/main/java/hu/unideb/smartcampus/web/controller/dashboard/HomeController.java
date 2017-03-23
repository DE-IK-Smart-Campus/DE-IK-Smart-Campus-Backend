package hu.unideb.smartcampus.web.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO.
 */
@Controller
@RequestMapping(path = "/dashboard/home")
public class HomeController {

  /**
   * TODO.
   */
  private static final String HOME_VIEW = "dashboard/home";

  /**
   * TODO.
   * @return asd
   */
  @GetMapping
  public String loadHomeView() {
    return HOME_VIEW;
  }
}

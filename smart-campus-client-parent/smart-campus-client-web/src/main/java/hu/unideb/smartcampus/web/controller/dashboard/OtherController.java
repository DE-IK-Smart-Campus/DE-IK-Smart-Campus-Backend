package hu.unideb.smartcampus.web.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO.
 */
@Controller
@RequestMapping(path = "/dashboard/other")
public class OtherController {

  /**
   * TODO.
   */
  private static final String OTHER_VIEW = "dashboard/other";

  /**
   * TODO.
   * @return asd
   */
  @GetMapping
  public String loadOtherView() {
    return OTHER_VIEW;
  }
}


package hu.unideb.smartcampus.web.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO.
 */
@Controller
@RequestMapping(path = "/dashboard/about-us")
public class AboutUsController {

  /**
   * TODO.
   */
  private static final String ABOUT_US_VIEW = "dashboard/about-us";

  /**
   * TODO.
   * @return asd
   */
  @GetMapping
  public String loadAboutUsView() {
    return ABOUT_US_VIEW;
  }
}


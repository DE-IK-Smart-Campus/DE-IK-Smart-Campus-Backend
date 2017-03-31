package hu.unideb.smartcampus.web.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

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
   */
  private static final String CURRENT_USERNAME_MODEL_OBJECT_NAME = "currentUsername";

  /**
   * TODO.
   * @return asd
   */
  @GetMapping
  public ModelAndView loadAboutUsView(final Principal principal) {
    final ModelAndView modelAndView = new ModelAndView(ABOUT_US_VIEW);
    final String name = principal.getName();
    modelAndView.addObject(CURRENT_USERNAME_MODEL_OBJECT_NAME, name);
    return modelAndView;
  }
}


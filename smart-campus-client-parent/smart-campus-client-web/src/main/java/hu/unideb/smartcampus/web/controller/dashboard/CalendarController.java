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
@RequestMapping(path = "/dashboard/calendar")
public class CalendarController {

  /**
   * TODO.
   */
  private static final String CALENDAR_VIEW = "dashboard/calendar";
  /**
   * TODO.
   */
  private static final String CURRENT_USERNAME_MODEL_OBJECT_NAME = "currentUsername";

  /**
   * TODO.
   * @return asd
   */
  @GetMapping
  public ModelAndView loadCalendarView(final Principal principal) {
    final ModelAndView modelAndView = new ModelAndView(CALENDAR_VIEW);
    final String name = principal.getName();
    modelAndView.addObject(CURRENT_USERNAME_MODEL_OBJECT_NAME, name);
    return modelAndView;
  }
}

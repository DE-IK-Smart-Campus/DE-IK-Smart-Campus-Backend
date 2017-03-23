package hu.unideb.smartcampus.web.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
   * @return asd
   */
  @GetMapping
  public String loadCalendarView() {
    return CALENDAR_VIEW;
  }
}

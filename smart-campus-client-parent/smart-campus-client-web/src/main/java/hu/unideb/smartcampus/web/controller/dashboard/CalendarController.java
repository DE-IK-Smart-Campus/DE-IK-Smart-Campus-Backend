package hu.unideb.smartcampus.web.controller.dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import hu.unideb.smartcampus.domain.calendar.CalendarSubject;
import hu.unideb.smartcampus.service.api.CalendarService;

/**
 * TODO.
 */
@Controller
@RequestMapping(path = "/dashboard/calendar")
public class CalendarController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CalendarController.class);

  @Autowired
  private CalendarService calendarService;

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

    final List<CalendarSubject> calendarSubjectList = calendarService.getCalendarSubjects(Long.MIN_VALUE, Long.MAX_VALUE);
    for (CalendarSubject calendarSubject : calendarSubjectList) {
      LOGGER.info(calendarSubject.toString());
    }

    modelAndView.addObject(CURRENT_USERNAME_MODEL_OBJECT_NAME, name);
    return modelAndView;
  }
}

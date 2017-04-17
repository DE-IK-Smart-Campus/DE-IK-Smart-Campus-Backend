package hu.unideb.smartcampus.web.controller.dashboard;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import hu.unideb.smartcampus.service.api.CalendarService;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

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
  private static final String REDIRECT_URL_TO_CALENDAR_VIEW = "redirect:/" + CALENDAR_VIEW;

  /**
   * TODO.
   */
  private static final String CURRENT_USERNAME_MODEL_OBJECT_NAME = "currentUsername";
  /**
   * TODO.
   */
  private static final String CUSTOM_EVENT_LIST_MODEL_OBJECT_NAME = "customEvents";

  @Autowired
  private CalendarService calendarService;

  /**
   * TODO.
   * @return asd
   */
  @GetMapping
  public ModelAndView loadCalendarView(final Principal principal) {
    final ModelAndView modelAndView = new ModelAndView(CALENDAR_VIEW);
    final String name = principal.getName();
    modelAndView.addObject(CURRENT_USERNAME_MODEL_OBJECT_NAME, name);

    modelAndView.addObject(CUSTOM_EVENT_LIST_MODEL_OBJECT_NAME, calendarService.getCustomEventsAsCustomEventList());
    return modelAndView;
  }

  @PostMapping(path = "/addcustomevent")
  public ModelAndView addCustomEvent(
      @RequestParam final String eventName,
      @RequestParam final String eventDescription,
      @RequestParam final String eventPlace,
      @RequestParam final Long eventStart,
      @RequestParam final Long eventEnd
  ) {
    final ModelAndView modelAndView = new ModelAndView(REDIRECT_URL_TO_CALENDAR_VIEW);
    calendarService.addCustomEvent(new CustomEventIqElement(
        eventName,
        eventDescription,
        eventPlace,
        eventStart,
        eventEnd,
        null,
        null,
        null,
        null
    ));
    return modelAndView;
  }

  @DeleteMapping(path = "/removecustomevent/{guid}")
  public ModelAndView deleteCustomEvent(@PathVariable final String guid) {
    final ModelAndView modelAndView = new ModelAndView(REDIRECT_URL_TO_CALENDAR_VIEW);
    calendarService.deleteCustomEvent(guid);
    return modelAndView;
  }
}

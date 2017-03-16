package hu.unideb.smartcampus.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * TODO.
 */
@Controller
@RequestMapping(path = "/dashboard")
public class DashboardController {

  /**
   * TODO.
   */
  private static final String TABS_MODEL_OBJECT_NAME = "tabs";
  /**
   * TODO.
   */
  private static final String CURRENT_TAB_MODEL_OBJECT_NAME = "currentTab";

  /**
   * TODO.
   */
  private static final String HOME = "home";
  /**
   * TODO.
   */
  private static final String CALENDAR = "calendar";
  /**
   * TODO.
   */
  private static final String CONSULTING_HOURS = "consulting-hours";
  /**
   * TODO.
   */
  private static final String CHAT = "chat";
  /**
   * TODO.
   */
  private static final String OTHER = "other";
  /**
   * TODO.
   */
  private static final String ABOUT_US = "about-us";

  /**
   * TODO.
   */
  private static final List<String> TABS = Arrays.asList(HOME, CALENDAR, CONSULTING_HOURS, CHAT, OTHER, ABOUT_US);

  /**
   * TODO.
   */
  private static final String DASHBOARD_VIEW = "dashboard";

  /**
   * TODO.
   * @return asd
   */
  @GetMapping
  public ModelAndView redirectToHomeTab() {
    final ModelAndView modelAndView = new ModelAndView(DASHBOARD_VIEW);
    modelAndView.addObject(CURRENT_TAB_MODEL_OBJECT_NAME, HOME);
    modelAndView.addObject(TABS_MODEL_OBJECT_NAME, TABS);
    return modelAndView;
  }

  /**
   * TODO.
   * @return asd
   */
  @GetMapping(path = "/home")
  public ModelAndView switchToHomeTab() {
    final ModelAndView modelAndView = new ModelAndView(DASHBOARD_VIEW);
    modelAndView.addObject(CURRENT_TAB_MODEL_OBJECT_NAME, HOME);
    modelAndView.addObject(TABS_MODEL_OBJECT_NAME, TABS);
    return modelAndView;
  }

  /**
   * TODO.
   * @return asd
   */
  @GetMapping(path = "/calendar")
  public ModelAndView switchToCalendarTab() {
    final ModelAndView modelAndView = new ModelAndView(DASHBOARD_VIEW);
    modelAndView.addObject(CURRENT_TAB_MODEL_OBJECT_NAME, CALENDAR);
    modelAndView.addObject(TABS_MODEL_OBJECT_NAME, TABS);
    return modelAndView;
  }

  /**
   * TODO.
   * @return asd
   */
  @GetMapping(path = "/consulting-hours")
  public ModelAndView switchToConsultingHoursTab() {
    final ModelAndView modelAndView = new ModelAndView(DASHBOARD_VIEW);
    modelAndView.addObject(CURRENT_TAB_MODEL_OBJECT_NAME, CONSULTING_HOURS);
    modelAndView.addObject(TABS_MODEL_OBJECT_NAME, TABS);
    return modelAndView;
  }

  /**
   * TODO.
   * @return asd
   */
  @GetMapping(path = "/chat")
  public ModelAndView switchToChatTab() {
    final ModelAndView modelAndView = new ModelAndView(DASHBOARD_VIEW);
    modelAndView.addObject(CURRENT_TAB_MODEL_OBJECT_NAME, CHAT);
    modelAndView.addObject(TABS_MODEL_OBJECT_NAME, TABS);
    return modelAndView;
  }

  /**
   * TODO.
   * @return asd
   */
  @GetMapping(path = "/other")
  public ModelAndView switchToOtherTab() {
    final ModelAndView modelAndView = new ModelAndView(DASHBOARD_VIEW);
    modelAndView.addObject(CURRENT_TAB_MODEL_OBJECT_NAME, OTHER);
    modelAndView.addObject(TABS_MODEL_OBJECT_NAME, TABS);
    return modelAndView;
  }

  /**
   * TODO.
   * @return asd
   */
  @GetMapping(path = "/about-us")
  public ModelAndView switchToAboutUsTab() {
    final ModelAndView modelAndView = new ModelAndView(DASHBOARD_VIEW);
    modelAndView.addObject(CURRENT_TAB_MODEL_OBJECT_NAME, ABOUT_US);
    modelAndView.addObject(TABS_MODEL_OBJECT_NAME, TABS);
    return modelAndView;
  }
}
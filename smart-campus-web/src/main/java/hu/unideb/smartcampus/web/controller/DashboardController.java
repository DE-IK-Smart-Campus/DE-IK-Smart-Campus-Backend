package hu.unideb.smartcampus.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO.
 */
@Controller
@RequestMapping(path = "/dashboard")
public class DashboardController {

  /**
   * TODO.
   */
  private static final String DASHBOARD_VIEW = "dashboard";

  /**
   * TODO.
   * @return asd
   */
  @GetMapping
  public String loadDashboardView() {
    return DASHBOARD_VIEW;
  }
}

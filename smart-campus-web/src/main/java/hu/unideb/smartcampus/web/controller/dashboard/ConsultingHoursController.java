package hu.unideb.smartcampus.web.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO.
 */
@Controller
@RequestMapping(path = "/dashboard/consulting-hours")
public class ConsultingHoursController {

  /**
   * TODO.
   */
  private static final String CONSULTING_HOURS_VIEW = "dashboard/consulting-hours";

  /**
   * TODO.
   * @return asd
   */
  @GetMapping
  public String loadConsultingHoursView() {
    return CONSULTING_HOURS_VIEW;
  }
}

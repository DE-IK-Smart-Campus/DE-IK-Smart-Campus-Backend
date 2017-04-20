package hu.unideb.smartcampus.web.controller.dashboard;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hu.unideb.smartcampus.domain.InstructorConsultingDate;
import hu.unideb.smartcampus.service.api.ConsultingHoursService;
import hu.unideb.smartcampus.service.api.authentication.SmartCampusUserDetails;

/**
 * TODO.
 */
@Controller
@RequestMapping(path = "/dashboard/instructor-consulting-hours")
public class InstructorConsultingHoursController {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(InstructorConsultingHoursController.class);

  @Autowired
  private ConsultingHoursService consultingHoursService;

  /**
   * TODO.
   */
  private static final String CONSULTING_HOURS_VIEW = "dashboard/instructor-consulting-hours";

  /**
   * TODO.
   */
  private static final String CURRENT_USERNAME_MODEL_OBJECT_NAME = "currentUsername";
  /**
   * TODO.
   */
  private static final String IS_STAFF_MODEL_OBJECT_NAME = "isStaff";
  /**
   * TODO.
   */
  private static final String SUBJECT_LIST_MODEL_OBJECT_NAME = "dateList";

  /**
   * TODO.
   * 
   * @return model and view.
   */
  @GetMapping
  public ModelAndView loadConsultingHoursView(final Principal principal) {
    final ModelAndView modelAndView = new ModelAndView(CONSULTING_HOURS_VIEW);
    final String name = principal.getName();
    UsernamePasswordAuthenticationToken details = (UsernamePasswordAuthenticationToken) principal;
    SmartCampusUserDetails userDetails = (SmartCampusUserDetails) details.getPrincipal();
    modelAndView.addObject(IS_STAFF_MODEL_OBJECT_NAME, userDetails.getRoles().stream().anyMatch(role -> role.getAuthority().equals("ROLE_STAFF")));
    modelAndView.addObject(CURRENT_USERNAME_MODEL_OBJECT_NAME, name);

    final List<InstructorConsultingDate> dates = consultingHoursService.listThisWeekSignedStudents();
    modelAndView.addObject(SUBJECT_LIST_MODEL_OBJECT_NAME, dates);

    return modelAndView;
  }

}

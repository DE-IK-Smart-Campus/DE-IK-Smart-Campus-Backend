package hu.unideb.smartcampus.web.controller.dashboard;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import hu.unideb.smartcampus.domain.ConsultingDate;
import hu.unideb.smartcampus.domain.Instructor;
import hu.unideb.smartcampus.domain.Subject;
import hu.unideb.smartcampus.service.api.ConsultingHoursService;
import hu.unideb.smartcampus.service.api.authentication.SmartCampusUserDetails;

/**
 * TODO.
 */
@Controller
@RequestMapping(path = "/dashboard/consulting-hours")
public class ConsultingHoursController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConsultingHoursController.class);

  @Autowired
  private ConsultingHoursService consultingHoursService;

  /**
   * TODO.
   */
  private static final String CONSULTING_HOURS_VIEW = "dashboard/consulting-hours";
  /**
   * TODO.
   */
  private static final String CONSULTING_HOURS_INSTRUCTOR_VIEW = "dashboard/consulting-hours/instructor";
  /**
   * TODO.
   */
  private static final String CONSULTING_HOURS_CONSULTING_DATE_SIGN_UP_VIEW = "dashboard/consulting-hours/consulting-date-sign-up";

  /**
   * TODO.
   */
  private static final String REDIRECT_URL_TO_CONSULTING_HOURS_PATH = "redirect:/dashboard/consulting-hours";

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
  private static final String SUBJECT_LIST_MODEL_OBJECT_NAME = "subjectList";
  /**
   * TODO.
   */
  private static final String INSTRUCTOR_MODEL_OBJECT_NAME = "instructor";
  /**
   * TODO.
   */
  private static final String INSTRUCTOR_NAME_MODEL_OBJECT_NAME = "instructorName";
  /**
   * TODO.
   */
  private static final String CONSULTING_DATE_MODEL_OBJECT_NAME = "consultingDate";

  /**
   * TODO.
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

    final List<Subject> subjects = consultingHoursService.getSubjects();
    modelAndView.addObject(SUBJECT_LIST_MODEL_OBJECT_NAME, subjects);

    return modelAndView;
  }

  /**
   * TODO.
   * @return model and view.
   */
  @GetMapping("/instructor/{instructorId}")
  public ModelAndView loadInstructorView(final Principal principal, @PathVariable final Long instructorId) {
    final ModelAndView modelAndView = new ModelAndView(CONSULTING_HOURS_INSTRUCTOR_VIEW);
    final String name = principal.getName();
    UsernamePasswordAuthenticationToken details = (UsernamePasswordAuthenticationToken) principal;
    SmartCampusUserDetails userDetails = (SmartCampusUserDetails) details.getPrincipal();
    modelAndView.addObject(IS_STAFF_MODEL_OBJECT_NAME, userDetails.getRoles().stream().anyMatch(role -> role.getAuthority().equals("ROLE_STAFF")));
    modelAndView.addObject(CURRENT_USERNAME_MODEL_OBJECT_NAME, name);

    final Instructor instructor = consultingHoursService.getInstructorByInstructorId(instructorId);
    modelAndView.addObject(INSTRUCTOR_MODEL_OBJECT_NAME, instructor);

    return modelAndView;
  }

  /**
   * TODO.
   * @return model and view.
   */
  @GetMapping("/instructor/{instructorId}/consulting-date/{consultingDateId}")
  public ModelAndView loadConsultingDateSignUpView(
      final Principal principal,
      @PathVariable final Long instructorId,
      @PathVariable final Long consultingDateId
  ) {
    final ModelAndView modelAndView = new ModelAndView(CONSULTING_HOURS_CONSULTING_DATE_SIGN_UP_VIEW);
    final String name = principal.getName();
    UsernamePasswordAuthenticationToken details = (UsernamePasswordAuthenticationToken) principal;
    SmartCampusUserDetails userDetails = (SmartCampusUserDetails) details.getPrincipal();
    modelAndView.addObject(IS_STAFF_MODEL_OBJECT_NAME, userDetails.getRoles().stream().anyMatch(role -> role.getAuthority().equals("ROLE_STAFF")));
    modelAndView.addObject(CURRENT_USERNAME_MODEL_OBJECT_NAME, name);

    final Instructor instructor = consultingHoursService.getInstructorByInstructorId(instructorId);
    modelAndView.addObject(INSTRUCTOR_NAME_MODEL_OBJECT_NAME, instructor.getName());

    final ConsultingDate consultingDate = instructor.getConsultingDates().stream()
        .filter(date -> date.getId().equals(consultingDateId))
        .findFirst()
        .orElse(null);
    modelAndView.addObject(CONSULTING_DATE_MODEL_OBJECT_NAME, consultingDate);

    return modelAndView;
  }

  /**
   * TODO.
   */
  @PostMapping("/{consultingHourId}")
  public String signUpForConsultingDate(
      @PathVariable final Long consultingHourId,
      @RequestParam final Long duration,
      @RequestParam final String reason
  ) {
    consultingHoursService.signUpForConsultingDate(consultingHourId, duration, reason);
    return REDIRECT_URL_TO_CONSULTING_HOURS_PATH;
  }
}

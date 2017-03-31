package hu.unideb.smartcampus.web.controller.dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import hu.unideb.smartcampus.service.api.ConsultingHoursService;
import hu.unideb.smartcampus.shared.iq.request.SubjectsIqRequest;

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
  private static final String SUBJECT_RETRIEVAL_RESPONSE_MODEL_OBJECT_NAME = "subjectRetrievalResponse";
  /**
   * TODO.
   */
  private static final String CURRENT_USERNAME_MODEL_OBJECT_NAME = "currentUsername";

  /**
   * TODO.
   * @return model and view.
   */
  @GetMapping
  public ModelAndView loadConsultingHoursView(final Principal principal) {
    final ModelAndView modelAndView = new ModelAndView(CONSULTING_HOURS_VIEW);
    final String name = principal.getName();
    modelAndView.addObject(CURRENT_USERNAME_MODEL_OBJECT_NAME, name);

    final SubjectsIqRequest subjects = consultingHoursService.getSubjects();
    modelAndView.addObject(SUBJECT_RETRIEVAL_RESPONSE_MODEL_OBJECT_NAME, subjects);

    return modelAndView;
  }

  /**
   * TODO.
   * @return model and view.
   */
  @GetMapping
  public ModelAndView loadInstructorViewByInstructorId(final Principal principal, @RequestParam(value = "id") Long instructorId) {
    final ModelAndView modelAndView = new ModelAndView(CONSULTING_HOURS_INSTRUCTOR_VIEW);
    final String name = principal.getName();
    modelAndView.addObject(CURRENT_USERNAME_MODEL_OBJECT_NAME, name);

    final SubjectsIqRequest subjects = consultingHoursService.getSubjects();
    modelAndView.addObject(SUBJECT_RETRIEVAL_RESPONSE_MODEL_OBJECT_NAME, subjects);

    return modelAndView;
  }
}

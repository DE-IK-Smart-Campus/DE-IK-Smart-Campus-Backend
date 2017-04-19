package hu.unideb.smartcampus.web.controller.dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import hu.unideb.smartcampus.domain.SubjectModel;
import hu.unideb.smartcampus.domain.calendar.CalendarSubject;
import hu.unideb.smartcampus.service.api.AttendanceService;

/**
 * TODO.
 */
@Controller
@RequestMapping(path = "/dashboard/attendance")
public class AttendanceController {

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(AttendanceController.class);

  /**
   * TODO.
   */
  private static final String ATTENDANCE_VIEW = "dashboard/attendance";
  /**
   * TODO.
   */
  private static final String SUBJECT_ATTENDANCE_VIEW = "dashboard/attendance/subject-attendance";
  /**
   * TODO.
   */
  private static final String REDIRECT_URL_TO_ATTENDANCE_VIEW = "redirect:/" + ATTENDANCE_VIEW;
  /**
   * TODO.
   */
  private static final String CURRENT_USERNAME_MODEL_OBJECT_NAME = "currentUsername";
  /**
   * TODO.
   */
  private static final String SUBJECTS_MODEL_OBJECT_NAME = "subjects";
  /**
   * TODO.
   */
  private static final String SUBJECT_MODEL_OBJECT_NAME = "subjectModel";

  @Autowired
  private AttendanceService attendanceService;

  /**
   * TODO.
   * @return asd
   */
  @GetMapping
  public ModelAndView loadAttendanceView(final Principal principal) {
    final ModelAndView modelAndView = new ModelAndView(ATTENDANCE_VIEW);
    final String name = principal.getName();
    modelAndView.addObject(CURRENT_USERNAME_MODEL_OBJECT_NAME, name);
    List<CalendarSubject> subjects = attendanceService.listSubjectsWithAttendance();
    modelAndView.addObject(SUBJECTS_MODEL_OBJECT_NAME, subjects);
    return modelAndView;
  }

  @GetMapping(path = "/{id}")
  public ModelAndView loadSubjectAttendanceView(final Principal principal, @RequestParam String id) {
    final ModelAndView modelAndView = new ModelAndView(SUBJECT_ATTENDANCE_VIEW);
    final String name = principal.getName();
    modelAndView.addObject(CURRENT_USERNAME_MODEL_OBJECT_NAME, name);
    SubjectModel subjectModel = new SubjectModel();
    subjectModel.setSubject(attendanceService.getSubjectWithAttendanceById(Long.parseLong(id)));
    modelAndView.addObject(SUBJECT_MODEL_OBJECT_NAME, subjectModel);
    return modelAndView;
  }

  @PostMapping
  public ModelAndView updateAttendances(@ModelAttribute SubjectModel subjectModel) {
    final ModelAndView modelAndView = new ModelAndView(REDIRECT_URL_TO_ATTENDANCE_VIEW);
    // set all false
    attendanceService.getSubjectWithAttendanceById(subjectModel.getSubject().getId()).getAppointmentTimes()
        .forEach(appointmentTime -> attendanceService.updateAppointmentById(appointmentTime.getId(), false));
    // set true the ones which came back
    subjectModel.getSubject().getAppointmentTimes()
        .forEach(appointmentTime -> attendanceService.updateAppointmentById(appointmentTime.getId(), true));
    return modelAndView;
  }
}

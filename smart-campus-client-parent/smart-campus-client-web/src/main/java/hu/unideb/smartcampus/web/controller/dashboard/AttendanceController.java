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
import org.springframework.web.servlet.ModelAndView;

import hu.unideb.smartcampus.domain.calendar.CalendarSubject;
import hu.unideb.smartcampus.service.api.AttendanceService;
import hu.unideb.smartcampus.service.api.authentication.SmartCampusUserDetails;
import hu.unideb.smartcampus.shared.util.DateUtil;

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
  private static final String REDIRECT_URL_TO_SUBJECT_ATTENDANCE_VIEW = "redirect:/" + ATTENDANCE_VIEW;
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
  private static final String SUBJECT_MODEL_OBJECT_NAME = "subject";
  /**
   * TODO.
   */
  private static final String IS_STAFF_MODEL_OBJECT_NAME = "isStaff";

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
    UsernamePasswordAuthenticationToken details = (UsernamePasswordAuthenticationToken) principal;
    SmartCampusUserDetails userDetails = (SmartCampusUserDetails) details.getPrincipal();
    modelAndView.addObject(IS_STAFF_MODEL_OBJECT_NAME, userDetails.getRoles().stream().anyMatch(role -> role.getAuthority().equals("ROLE_STAFF")));
    modelAndView.addObject(CURRENT_USERNAME_MODEL_OBJECT_NAME, name);
    List<CalendarSubject> subjects = attendanceService.listSubjectsWithAttendance();
    modelAndView.addObject(SUBJECTS_MODEL_OBJECT_NAME, subjects);
    return modelAndView;
  }

  @GetMapping(path = "/{subjectId}")
  public ModelAndView loadSubjectAttendanceView(final Principal principal, @PathVariable String subjectId) {
    final ModelAndView modelAndView = new ModelAndView(SUBJECT_ATTENDANCE_VIEW);
    final String name = principal.getName();
    UsernamePasswordAuthenticationToken details = (UsernamePasswordAuthenticationToken) principal;
    SmartCampusUserDetails userDetails = (SmartCampusUserDetails) details.getPrincipal();
    modelAndView.addObject(IS_STAFF_MODEL_OBJECT_NAME, userDetails.getRoles().stream().anyMatch(role -> role.getAuthority().equals("ROLE_STAFF")));
    modelAndView.addObject(CURRENT_USERNAME_MODEL_OBJECT_NAME, name);
    final CalendarSubject subject = attendanceService.getSubjectWithAttendanceById(Long.parseLong(subjectId));
    subject.getAppointmentTimes().forEach(appointmentTime -> appointmentTime.setWhenInLocalDate(DateUtil.getInLocalDateByEpochSecond(appointmentTime.getWhen().getTime()/1000)));
    modelAndView.addObject(SUBJECT_MODEL_OBJECT_NAME, subject);
    return modelAndView;
  }

  @PostMapping(path = "/{subjectId}/{appointmentId}/{present}")
  public ModelAndView updateAttendances(@PathVariable String subjectId, @PathVariable String appointmentId, @PathVariable String present) {
    final ModelAndView modelAndView = new ModelAndView(REDIRECT_URL_TO_SUBJECT_ATTENDANCE_VIEW + "/" + subjectId);
    attendanceService.updateAppointmentById(Long.parseLong(appointmentId), !Boolean.parseBoolean(present));
    return modelAndView;
  }
}

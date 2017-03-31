package hu.unideb.smartcampus.web.controller.dashboard;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.StanzaCollector;
import org.jivesoftware.smack.packet.IQ;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import hu.unideb.smartcampus.service.api.ConsultingHoursService;
import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.iq.request.InstructorConsultingDatesIqRequest;
import hu.unideb.smartcampus.shared.iq.request.SubjectsIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.ConsultingDateIqElement;

/**
 * TODO.
 */
@Controller
@RequestMapping(path = "/dashboard/consulting-hours")
public class ConsultingHoursController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConsultingHoursController.class);

  @Autowired
  private ConsultingHoursService consultingHoursService;

  @Autowired
  private EjabberdUser ejabberdUser;

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
  private static final String CURRENT_USERNAME_MODEL_OBJECT_NAME = "currentUsername";
  /**
   * TODO.
   */
  private static final String SUBJECT_LIST_MODEL_OBJECT_NAME = "subjectList";
  /**
   * TODO.
   */
  private static final String INSTRUCTOR_NAME_MODEL_OBJECT_NAME = "instructorName";
  /**
   * TODO.
   */
  private static final String CONSULTING_DATE_LIST_MODEL_OBJECT_NAME = "consultingDateList";

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
    modelAndView.addObject(SUBJECT_LIST_MODEL_OBJECT_NAME, subjects);

    return modelAndView;
  }


  /**
   * TODO.
   * @return model and view.
   */
  @GetMapping("/instructor/{instructorId}")
  public ModelAndView loadInstructorViewByInstructorId(final Principal principal, @PathVariable final Long instructorId) {
    final ModelAndView modelAndView = new ModelAndView(CONSULTING_HOURS_INSTRUCTOR_VIEW);
    final String name = principal.getName();
    modelAndView.addObject(CURRENT_USERNAME_MODEL_OBJECT_NAME, name);

    String instructorName = "";
    List<ConsultingDateIqElement> consultingDateList = null;
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      InstructorConsultingDatesIqRequest iq = new InstructorConsultingDatesIqRequest();
      iq.setType(IQ.Type.get);
      iq.setFrom(connection.getUser());
      iq.setTo(JidCreate.from("smartcampus@smartcampus/Smartcampus"));
      iq.setInstructorId(instructorId.toString());
      StanzaCollector collector = connection.createStanzaCollectorAndSend(iq);
      InstructorConsultingDatesIqRequest resultIq = collector.nextResultBlockForever();
      instructorName = resultIq.getInstructorName();
      consultingDateList = resultIq.getConsultingDates();
    } catch (SmackException.NotConnectedException | InterruptedException | XmppStringprepException e) {
      LOGGER.error("Error while sending IQ", e);
      ResponseEntity.badRequest().body(e.getCause().getMessage());
    }

    modelAndView.addObject(INSTRUCTOR_NAME_MODEL_OBJECT_NAME, instructorName);
    modelAndView.addObject(CONSULTING_DATE_LIST_MODEL_OBJECT_NAME, consultingDateList);

    return modelAndView;
  }
}

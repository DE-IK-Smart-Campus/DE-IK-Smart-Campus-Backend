package hu.unideb.smartcampus.web.controller.dashboard;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaCollector;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.iq.request.SubjectsIqRequest;

/**
 * TODO.
 */
@Controller
@RequestMapping(path = "/dashboard/consulting-hours")
public class ConsultingHoursController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConsultingHoursController.class);

  @Autowired
  private EjabberdUser ejabberdUser;

  SubjectsIqRequest resultIq = null;

  /**
   * TODO.
   */
  private static final String CONSULTING_HOURS_VIEW = "dashboard/consulting-hours";

  /**
   * TODO.
   */
  private static final String SUBJECT_RETRIEVAL_RESPONSE_MODEL_OBJECT_NAME =
      "subjectRetrievalResponse";
  /**
   * TODO.
   */
  private static final String CURRENT_USERNAME_MODEL_OBJECT_NAME = "currentUsername";

  /**
   * Smartcampus JID.
   */
  private static final String SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS =
      "smartcampus@smartcampus/Smartcampus";

  /**
   * TODO.
   * @return model and view.
   */
  @GetMapping
  public ModelAndView loadConsultingHoursView(final Principal principal) {
    final ModelAndView modelAndView = new ModelAndView(CONSULTING_HOURS_VIEW);
    final String name = principal.getName();
    modelAndView.addObject(CURRENT_USERNAME_MODEL_OBJECT_NAME, name);
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      SubjectsIqRequest iq = new SubjectsIqRequest();
      iq.setType(Type.get);
      iq.setFrom(connection.getUser());
      iq.setTo(JidCreate.from(SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS));
      iq.setStudent(getUser(connection));
      StanzaCollector collector = connection.createStanzaCollectorAndSend(iq);
      resultIq = collector.nextResult(10000);
    } catch (NotConnectedException | InterruptedException | XmppStringprepException e) {
      LOGGER.error("Error while sending IQ", e);
    }
    modelAndView.addObject(SUBJECT_RETRIEVAL_RESPONSE_MODEL_OBJECT_NAME, resultIq);
    return modelAndView;
  }

  private String getUser(AbstractXMPPConnection connection) {
    return connection.getUser().toString().split("@")[0];
  }
}

package hu.unideb.smartcampus.web.controller;


import java.util.List;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaCollector;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.service.api.xmpp.DefaultUser;
import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.iq.request.AddMucChatIqRequest;
import hu.unideb.smartcampus.shared.iq.request.AddUserChatIqRequest;
import hu.unideb.smartcampus.shared.iq.request.InstructorConsultingDatesIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListUserChatsIqRequest;
import hu.unideb.smartcampus.shared.iq.request.SignUpForConsultingDateIqRequest;
import hu.unideb.smartcampus.shared.iq.request.SubjectsIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.ConsultingDateIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.SubjectIqElement;


/**
 * Sample rest controller.
 */
@RestController
public class CustomIqRestController {

  /**
   * Smartcampus user JID.
   */
  private static final String SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS =
      "smartcampus@smartcampus/Smartcampus";

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CustomIqRestController.class);

  /**
   * Sample dependency.
   */
  private final EjabberdUser ejabberdUser;

  /**
   * Default user.
   */
  private final DefaultUser defaultUser;

  /**
   * Constructor for dependency injection.
   */
  @Autowired
  public CustomIqRestController(final EjabberdUser sampleService, final DefaultUser defaultUser) {
    this.ejabberdUser = sampleService;
    this.defaultUser = defaultUser;
  }

  /**
   * Sample endpoint.
   * 
   * @return {@link ResponseEntity}
   */
  @GetMapping(path = "/subjects")
  public ResponseEntity<List<SubjectIqElement>> sendSubjectRetrieval(
      @RequestParam(name = "user") String user) {
    SubjectsIqRequest resultIq = null;
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      SubjectsIqRequest iq = new SubjectsIqRequest();
      iq.setType(Type.get);
      iq.setFrom(connection.getUser());
      iq.setTo(JidCreate.from(SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS));
      iq.setStudent(user);
      StanzaCollector collector = connection.createStanzaCollectorAndSend(iq);
      resultIq = collector.nextResultBlockForever();
    } catch (NotConnectedException | InterruptedException | XmppStringprepException e) {
      LOGGER.error("Error while sending IQ", e);
      ResponseEntity.badRequest().body(e.getCause().getMessage());
    }
    return ResponseEntity.ok().body(resultIq.getSubjects());
  }

  /**
   * Sample endpoint.
   * 
   * @return {@link ResponseEntity}
   */
  @GetMapping(path = "/consulting")
  public ResponseEntity<List<ConsultingDateIqElement>> getInstructorConsultingDate(
      @RequestParam(name = "id") String id) {
    List<ConsultingDateIqElement> result = null;
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      InstructorConsultingDatesIqRequest iq = new InstructorConsultingDatesIqRequest();
      iq.setType(Type.get);
      iq.setFrom(connection.getUser());
      iq.setTo(JidCreate.from(SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS));
      iq.setInstructorId(id);
      StanzaCollector collector = connection.createStanzaCollectorAndSend(iq);
      InstructorConsultingDatesIqRequest resultIq = collector.nextResultBlockForever();
      result = resultIq.getConsultingDates();
    } catch (NotConnectedException | InterruptedException | XmppStringprepException e) {
      LOGGER.error("Error while sending IQ", e);
      ResponseEntity.badRequest().body(e.getCause().getMessage());
    }
    return ResponseEntity.ok().body(result);
  }

  /**
   * Sample endpoint.
   * 
   * @return {@link ResponseEntity}
   */
  @GetMapping(path = "/signUp")
  public ResponseEntity<String> signUpForConsultingDate(@RequestParam(name = "id") String id,
      @RequestParam(name = "userId") String userId) {
    SignUpForConsultingDateIqRequest result = null;
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      SignUpForConsultingDateIqRequest iq = new SignUpForConsultingDateIqRequest();
      iq.setType(Type.set);
      iq.setFrom(connection.getUser());
      iq.setTo(JidCreate.from(SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS));
      iq.setConsultingHourId(Long.valueOf(id));
      iq.setDuration("10 minutes");
      iq.setUserId(userId);
      iq.setReason("Cause I wanna talk man.");
      StanzaCollector collector = connection.createStanzaCollectorAndSend(iq);
      SignUpForConsultingDateIqRequest resultIq = collector.nextResultBlockForever();
      result = resultIq;
    } catch (NotConnectedException | InterruptedException | XmppStringprepException e) {
      LOGGER.error("Error while sending IQ", e);
      ResponseEntity.badRequest().body(e.getCause().getMessage());
    }
    return ResponseEntity.ok().body(result.getResponseMessage());
  }

  @GetMapping(path = "/add/chat/{student}")
  public ResponseEntity<String> addChatRoom(@PathVariable("student") String student,
      @RequestParam("jid") String jid) {
    AddUserChatIqRequest iq = new AddUserChatIqRequest();
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      iq.setType(Type.set);
      iq.setFrom(connection.getUser());
      iq.setTo(JidCreate.from(SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS));
      iq.setStudent(student);
      iq.setChat(jid);
      StanzaCollector collector = connection.createStanzaCollectorAndSend(iq);
      iq = collector.nextResultBlockForever();
    } catch (NotConnectedException | InterruptedException | XmppStringprepException e) {
      LOGGER.error("Error while sending IQ", e);
      ResponseEntity.badRequest().body(e.getCause().getMessage());
    }
    return ResponseEntity.ok().body("OK");
  }

  @GetMapping(path = "/add/muc/{student}")
  public ResponseEntity<String> addMucRoom(@PathVariable("student") String student,
      @RequestParam("jid") String jid) {
    AddMucChatIqRequest iq = new AddMucChatIqRequest();
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      iq.setType(Type.set);
      iq.setFrom(connection.getUser());
      iq.setTo(JidCreate.from(SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS));
      iq.setStudent(student);
      iq.setMuc(jid);
      StanzaCollector collector = connection.createStanzaCollectorAndSend(iq);
      iq = collector.nextResultBlockForever();
    } catch (NotConnectedException | InterruptedException | XmppStringprepException e) {
      LOGGER.error("Error while sending IQ", e);
      ResponseEntity.badRequest().body(e.getCause().getMessage());
    }
    return ResponseEntity.ok().body("OK");
  }

  @GetMapping(path = "/list/muc/{student}")
  public ResponseEntity<List<String>> listMuc(@PathVariable("student") String student) {
    ListUserChatsIqRequest iq = new ListUserChatsIqRequest();
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      iq.setType(Type.set);
      iq.setFrom(connection.getUser());
      iq.setTo(JidCreate.from(SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS));
      iq.setStudent(student);
      StanzaCollector collector = connection.createStanzaCollectorAndSend(iq);
      iq = collector.nextResultBlockForever();
    } catch (NotConnectedException | InterruptedException | XmppStringprepException e) {
      LOGGER.error("Error while sending IQ", e);
      ResponseEntity.badRequest().body(e.getCause().getMessage());
    }
    return ResponseEntity.ok().body(iq.getMucChatList());
  }

  @GetMapping(path = "/list/single/{student}")
  public ResponseEntity<List<String>> listSingleChat(@PathVariable("student") String student) {
    ListUserChatsIqRequest iq = new ListUserChatsIqRequest();
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      iq.setType(Type.set);
      iq.setFrom(connection.getUser());
      iq.setTo(JidCreate.from(SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS));
      iq.setStudent(student);
      StanzaCollector collector = connection.createStanzaCollectorAndSend(iq);
      iq = collector.nextResultBlockForever();
    } catch (NotConnectedException | InterruptedException | XmppStringprepException e) {
      LOGGER.error("Error while sending IQ", e);
      ResponseEntity.badRequest().body(e.getCause().getMessage());
    }
    return ResponseEntity.ok().body(iq.getChatList());
  }

  /**
   * Sample endpoint.
   * 
   * @return {@link ResponseEntity}
   */
  @GetMapping(path = "/smartcampusRe")
  public ResponseEntity<String> reconnectSmartcampusUser() {
    ResponseEntity<String> body = ResponseEntity.ok().body("OK");
    defaultUser.reconnect();
    return body;
  }

}

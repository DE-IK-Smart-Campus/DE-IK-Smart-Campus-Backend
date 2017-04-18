package hu.unideb.smartcampus.web.rest;

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

import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.iq.request.ChangeAttendanceIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListUserAttendanceIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;

@RestController
public class AttendanceTestRestController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AttendanceTestRestController.class);

  /**
   * Sample dependency.
   */
  private final EjabberdUser ejabberdUser;

  /**
   * Default user.
   */

  /**
   * Constructor for dependency injection.
   */
  @Autowired
  public AttendanceTestRestController(final EjabberdUser sampleService) {
    this.ejabberdUser = sampleService;
  }

  /**
   * Smartcampus user JID.
   */
  private static final String SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS =
      "smartcampus@smartcampus/Smartcampus";

  @GetMapping(path = "/attendance/change/{student}")
  public ResponseEntity<String> addChatRoom(@PathVariable("student") String student,
      @RequestParam("id") Long appointmentId, @RequestParam("present") String present) {
    ChangeAttendanceIqRequest iq = new ChangeAttendanceIqRequest();
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      iq.setType(Type.set);
      iq.setFrom(connection.getUser());
      iq.setTo(JidCreate.from(SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS));
      iq.setStudent(student);
      iq.setAppointmentId(appointmentId);
      iq.setPresent(Boolean.valueOf(present));
      StanzaCollector collector = connection.createStanzaCollectorAndSend(iq);
      iq = collector.nextResultBlockForever();
    } catch (NotConnectedException | InterruptedException | XmppStringprepException e) {
      LOGGER.error("Error while sending IQ", e);
      ResponseEntity.badRequest().body(e.getCause().getMessage());
    }
    return ResponseEntity.ok().body("OK");
  }


  @GetMapping(path = "/attendance/list/{student}")
  public ResponseEntity<List<CalendarSubjectIqElement>> listMuc(@PathVariable("student") String student) {
    ListUserAttendanceIqRequest iq = new ListUserAttendanceIqRequest();
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      iq.setType(Type.get);
      iq.setFrom(connection.getUser());
      iq.setTo(JidCreate.from(SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS));
      iq.setStudent(student);
      StanzaCollector collector = connection.createStanzaCollectorAndSend(iq);
      iq = collector.nextResultBlockForever();
    } catch (NotConnectedException | InterruptedException | XmppStringprepException e) {
      LOGGER.error("Error while sending IQ", e);
      ResponseEntity.badRequest().body(e.getCause().getMessage());
    }
    return ResponseEntity.ok().body(iq.getSubjectEvents());
  }



}

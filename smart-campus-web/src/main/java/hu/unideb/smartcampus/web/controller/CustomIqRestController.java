package hu.unideb.smartcampus.web.controller;


import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.packet.IQ.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.service.api.xmpp.DefaultUser;
import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.iq.request.InstructorConsultingDatesIqRequest;
import hu.unideb.smartcampus.shared.iq.request.SubjectsIqRequest;


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
  public ResponseEntity<String> sendSubjectRetrieval(@RequestParam(name = "user") String user) {
    String result = "OK";
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      SubjectsIqRequest iq = new SubjectsIqRequest();
      iq.setType(Type.get);
      iq.setFrom(connection.getUser());
      iq.setTo(SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS);
      iq.setStudent(user);
      PacketCollector collector = connection.createPacketCollectorAndSend(iq);
      SubjectsIqRequest resultIq = collector.nextResultBlockForever();
      result = resultIq.toString();
    } catch (NotConnectedException e) {
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
  @GetMapping(path = "/consulting")
  public ResponseEntity<String> getInstrcutorConsultingDate(@RequestParam(name = "id") String id) {
    String result = "OK";
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      InstructorConsultingDatesIqRequest iq = new InstructorConsultingDatesIqRequest();
      iq.setType(Type.get);
      iq.setFrom(connection.getUser());
      iq.setTo(SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS);
      iq.setInstructorId(id);
      PacketCollector collector = connection.createPacketCollectorAndSend(iq);
      InstructorConsultingDatesIqRequest resultIq = collector.nextResultBlockForever();
      result = resultIq.toString();
    } catch (NotConnectedException e) {
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
  @GetMapping(path = "/smartcampusRe")
  public ResponseEntity<String> reconnectSmartcampusUser() {
    ResponseEntity<String> body = ResponseEntity.ok().body("OK");
    defaultUser.reconnect();
    return body;
  }

}

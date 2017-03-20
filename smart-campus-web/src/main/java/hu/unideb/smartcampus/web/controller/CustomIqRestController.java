package hu.unideb.smartcampus.web.controller;


import java.util.Arrays;
import java.util.List;

import org.jivesoftware.smack.AbstractXMPPConnection;
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
import hu.unideb.smartcampus.shared.iq.SubjectsIq;
import hu.unideb.smartcampus.shared.iq.TestIq;
import hu.unideb.smartcampus.shared.iq.TestIq.Thing;


/**
 * Sample rest controller.
 */
@RestController
public class CustomIqRestController {

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
  @GetMapping(path = "/sendIq")
  public ResponseEntity<String> sendIq() {
    ResponseEntity<String> body = ResponseEntity.ok().body("OK");
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      List<Thing> things =
          Arrays.asList(new Thing("Uj IQ provider", "Smart campus appra a legjobb"));
      TestIq packet = new TestIq("smartcampus@smartcampus", things);
      packet.setTo("smartcampus@smartcampus/Smartcampus");
      packet.setType(Type.set);
      packet.setFrom(connection.getUser());
      connection.sendStanza(packet);
    } catch (NotConnectedException e) {
      LOGGER.error("Error while sending IQ", e);
      ResponseEntity.badRequest().body(e.getCause().getMessage());
    }
    return body;
  }
  
  /**
   * Sample endpoint.
   * 
   * @return {@link ResponseEntity}
   */
  @GetMapping(path = "/subjects")
  public ResponseEntity<String> sendSubjectRetrieval(@RequestParam(name = "user") String user) {
    ResponseEntity<String> body = ResponseEntity.ok().body("OK");
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      SubjectsIq iq = new SubjectsIq();
      iq.setType(Type.get);
      iq.setFrom(connection.getUser());
      iq.setTo(connection.getUser());
      iq.setStudent(user);
      connection.sendStanza(iq);
    } catch (NotConnectedException e) {
      LOGGER.error("Error while sending IQ", e);
      ResponseEntity.badRequest().body(e.getCause().getMessage());
    }
    return body;
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

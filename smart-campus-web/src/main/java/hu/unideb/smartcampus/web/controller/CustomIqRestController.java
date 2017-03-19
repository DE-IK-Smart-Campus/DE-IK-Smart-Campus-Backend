package hu.unideb.smartcampus.web.controller;


import java.util.Collections;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.packet.IQ.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.iq.TestIq;


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
   * Constructor for dependency injection.
   */
  @Autowired
  public CustomIqRestController(final EjabberdUser sampleService) {
    this.ejabberdUser = sampleService;
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
      TestIq packet = new TestIq("smartcampus@smartcampus", Collections.emptyList());
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
}

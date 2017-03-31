package hu.unideb.smartcampus.web.rest;

import java.util.List;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaCollector;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.iq.request.CustomEventListIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

/**
 * Chat property REST controller.
 */
@RestController
@RequestMapping("/custom-events")
public class CustomEventController {

  private static final String SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS = "smartcampus@smartcampus/Smartcampus";
  /**
   * Chat properties holder service.
   */
  @Autowired
  private EjabberdUser ejabberdUser;

  /**
   * Get Converse JS connection properties.
   */
  @GetMapping
  public ResponseEntity<List<CustomEventIqElement>> getCustomEvents() {
    CustomEventListIqRequest resultIq = null;
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      CustomEventListIqRequest iq = new CustomEventListIqRequest();
      iq.setType(Type.get);
      iq.setFrom(connection.getUser());
      iq.setTo(JidCreate.from(SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS));
      iq.setStudent(getUser(connection));
      StanzaCollector collector = connection.createStanzaCollectorAndSend(iq);
      resultIq = collector.nextResultBlockForever();
    } catch (NotConnectedException | InterruptedException | XmppStringprepException e) {
      ResponseEntity.badRequest().body(e.getCause().getMessage());
    }
    return ResponseEntity.ok().body(resultIq.getCustomEvents());
  }
  
  private String getUser(AbstractXMPPConnection connection) {
    return connection.getUser().toString().split("@")[0];
  }

}

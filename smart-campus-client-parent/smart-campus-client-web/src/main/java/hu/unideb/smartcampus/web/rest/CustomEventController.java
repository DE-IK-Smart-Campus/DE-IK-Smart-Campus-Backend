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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.iq.request.AddCustomEventIqRequest;
import hu.unideb.smartcampus.shared.iq.request.DeleteCustomEventIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListCustomEventIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

/**
 * Chat property REST controller.
 */
@RestController
@RequestMapping("/custom-events")
public class CustomEventController {

  private static final String SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS =
      "smartcampus@smartcampus/Smartcampus";
  /**
   * Chat properties holder service.
   */
  @Autowired
  private EjabberdUser ejabberdUser;

  /**
   * Get Converse JS connection properties.
   */
  @GetMapping("/list")
  public ResponseEntity<List<CustomEventIqElement>> getCustomEvents() {
    ListCustomEventIqRequest resultIq = null;
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      ListCustomEventIqRequest iq = new ListCustomEventIqRequest();
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

  /**
   * Get Converse JS connection properties.
   */
  @GetMapping("/add")
  public ResponseEntity addCustomEvent() {
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      AddCustomEventIqRequest iq = new AddCustomEventIqRequest();
      iq.setType(Type.set);
      iq.setFrom(connection.getUser());
      iq.setTo(JidCreate.from(SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS));
      iq.setStudent(getUser(connection));
      iq.setCustomEvent(
          CustomEventIqElement.builder()
              .eventName("Controllerbol")
              .eventStart(100L)
              .eventEnd(200L)
              .build());
      connection.sendStanza(iq);
    } catch (NotConnectedException | InterruptedException | XmppStringprepException e) {
      ResponseEntity.badRequest().body(e.getCause().getMessage());
    }
    return ResponseEntity.ok().build();
  }

  /**
   * Get Converse JS connection properties.
   */
  @GetMapping("/del")
  public ResponseEntity delCustomEvent(@RequestParam("id") String id) {
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      DeleteCustomEventIqRequest iq = new DeleteCustomEventIqRequest();
      iq.setType(Type.set);
      iq.setFrom(connection.getUser());
      iq.setTo(JidCreate.from(SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS));
      iq.setStudent(getUser(connection));
      iq.setEventGuid(id);
      connection.sendStanza(iq);
    } catch (NotConnectedException | InterruptedException | XmppStringprepException e) {
      ResponseEntity.badRequest().body(e.getCause().getMessage());
    }
    return ResponseEntity.ok().build();
  }


  private String getUser(AbstractXMPPConnection connection) {
    return connection.getUser().toString().split("@")[0];
  }

}


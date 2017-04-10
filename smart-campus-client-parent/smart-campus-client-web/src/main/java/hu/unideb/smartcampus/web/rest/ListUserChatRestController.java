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
import hu.unideb.smartcampus.shared.iq.request.AddMucChatIqRequest;
import hu.unideb.smartcampus.shared.iq.request.AddUserChatIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListUserChatsIqRequest;

@RestController
public class ListUserChatRestController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ListUserChatRestController.class);

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
  public ListUserChatRestController(final EjabberdUser sampleService) {
    this.ejabberdUser = sampleService;
  }

  /**
   * Smartcampus user JID.
   */
  private static final String SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS =
      "smartcampus@smartcampus/Smartcampus";

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
    return ResponseEntity.ok().body(iq.getMucChatList());
  }

  @GetMapping(path = "/list/single/{student}")
  public ResponseEntity<List<String>> listSingleChat(@PathVariable("student") String student) {
    ListUserChatsIqRequest iq = new ListUserChatsIqRequest();
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
    return ResponseEntity.ok().body(iq.getChatList());
  }

}

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
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.iq.request.ListInstructorConsultingDatesIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.StudentIqElement;

@RestController
public class ListInstructorSignedConsultingDates {

  private static final Logger LOGGER = LoggerFactory.getLogger(ListInstructorSignedConsultingDates.class);
  
  @Autowired
  private EjabberdUser ejabberdUser;

  @GetMapping("/list/instructorSignedStudentStudents/{neptunIdentifier}")
  public ResponseEntity<List<StudentIqElement>> listStudents(@PathVariable String neptunIdentifier) {
    ListInstructorConsultingDatesIqRequest resultIq = null;
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      ListInstructorConsultingDatesIqRequest iq = new ListInstructorConsultingDatesIqRequest();
      iq.setType(Type.get);
      iq.setFrom(connection.getUser());
      iq.setTo(JidCreate.from("smartcampus@smartcampus/Smartcampus"));
      iq.setInstructorId(neptunIdentifier);
      iq.setOneWeek(false);
      StanzaCollector collector = connection.createStanzaCollectorAndSend(iq);
      resultIq = collector.nextResultBlockForever();
    } catch (NotConnectedException | InterruptedException | XmppStringprepException e) {
      LOGGER.error("Error while sending IQ", e);
      ResponseEntity.badRequest().body(e.getCause().getMessage());
    }
    return ResponseEntity.ok().body(resultIq.getStudents());
  }
}

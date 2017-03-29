package hu.unideb.smartcampus.web.controller;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaCollector;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.iq.request.CalendarSubjectsIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;


@RestController
public class CalendarEventController {

  @Autowired
  private EjabberdUser ejabberdUser;


  @GetMapping(path = "/calendar/list")
  public ResponseEntity<List<CalendarSubjectIqElement>> getSubjectEventsByUserId(
      @RequestParam("user") String user) throws NotConnectedException, InterruptedException {
    AbstractXMPPConnection connection = ejabberdUser.getConnection();
    CalendarSubjectsIqRequest iq = CalendarSubjectsIqRequest.builder().student(user).build();
    iq.setTo("smartcampus@smartcampus/Smartcampus");
    iq.setFrom(connection.getUser());
    iq.setType(Type.get);
    iq.setStartPeriod(
        LocalDate.of(2017, 1, 1).atStartOfDay().toEpochSecond(ZoneOffset.ofHours(1)));
    iq.setEndPeriod(LocalDate.of(2017, 5, 31).atStartOfDay().toEpochSecond(ZoneOffset.ofHours(1)));
    StanzaCollector collector = connection.createStanzaCollectorAndSend(iq);
    CalendarSubjectsIqRequest result = collector.nextResultBlockForever();
    return ResponseEntity.ok(result.getSubjectEvents());
  }

}

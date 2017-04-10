package hu.unideb.smartcampus.web.rest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.Arrays;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.iq.request.GenerateOfficeHoursIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.OfficeHourIntervallIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.OfficeHourIqElement;

@RestController
public class GenerateOfficeHoursRestController {

  /**
   * Smartcampus user JID.
   */
  private static final String SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS =
      "smartcampus@smartcampus/Smartcampus";

  /**
   * Logger.
   */
  private static final Logger LOGGER =
      LoggerFactory.getLogger(GenerateOfficeHoursRestController.class);

  /**
   * Sample dependency.
   */
  private final EjabberdUser ejabberdUser;

  /**
   * Constructor for dependency injection.
   */
  @Autowired
  public GenerateOfficeHoursRestController(final EjabberdUser sampleService) {
    this.ejabberdUser = sampleService;
  }


  @GetMapping(path = "/officehours")
  public ResponseEntity<String> generateOfficeHour(
      @RequestParam("instructorId") Long instructorId,
      @RequestParam("day") Integer day,
      @RequestParam("from") String from,
      @RequestParam("to") String to) {
    LOGGER.info("/officehours");
    try {
      AbstractXMPPConnection connection = ejabberdUser.getConnection();
      GenerateOfficeHoursIqRequest iq = new GenerateOfficeHoursIqRequest();
      iq.setType(Type.set);
      iq.setFrom(connection.getUser());
      iq.setTo(JidCreate.from(SMARTCAMPUS_SMARTCAMPUS_SMARTCAMPUS));
      iq.setInstructorId(instructorId);
      iq.setIntervall(OfficeHourIntervallIqElement.builder()
          .fromDate(LocalDate.of(2017, Month.FEBRUARY, 20).atStartOfDay()
              .toEpochSecond(ZoneOffset.ofHours(1)) * 1000)
          .toDate(
              LocalDate.of(2017, Month.MAY, 26).atStartOfDay().toEpochSecond(ZoneOffset.ofHours(1))
                  * 1000)
          .build());
      iq.setOfficeHours(Arrays.asList(OfficeHourIqElement.builder()
          .day(DayOfWeek.of(day))
          .from(from)
          .to(to)
          .build()));
      StanzaCollector collector = connection.createStanzaCollectorAndSend(iq);
      GenerateOfficeHoursIqRequest resultIq = collector.nextResultBlockForever();
      LOGGER.info("{} hours generated.", resultIq.getCreatedHours());
    } catch (NotConnectedException | InterruptedException | XmppStringprepException e) {
      LOGGER.error("Error while sending IQ", e);
      return ResponseEntity.badRequest().body(e.getCause().getMessage());
    }
    LOGGER.info("officehours end");
    return ResponseEntity.ok("OK");
  }

}

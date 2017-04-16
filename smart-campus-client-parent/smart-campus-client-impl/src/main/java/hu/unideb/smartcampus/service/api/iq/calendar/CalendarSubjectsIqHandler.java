package hu.unideb.smartcampus.service.api.iq.calendar;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.packet.IQ;

import hu.unideb.smartcampus.service.api.iq.IqHandler;
import hu.unideb.smartcampus.shared.iq.request.CalendarSubjectsIqRequest;

public class CalendarSubjectsIqHandler extends IqHandler<CalendarSubjectsIqRequest> {
  public CalendarSubjectsIqHandler(AbstractXMPPConnection connection, String domain, Long startPeriod, Long endPeriod) {
    this.connection = connection;
    this.domain = domain;
    this.iq = new CalendarSubjectsIqRequest();
    this.iq.setType(IQ.Type.get);
    this.iq.setFrom(connection.getUser());
    this.setSmartcampusUser(iq);
    this.iq.setStartPeriod(startPeriod);
    this.iq.setEndPeriod(endPeriod);
    this.iq.setStudent(connection.getUser().asEntityBareJidString().split(AT)[0]);
  }
}

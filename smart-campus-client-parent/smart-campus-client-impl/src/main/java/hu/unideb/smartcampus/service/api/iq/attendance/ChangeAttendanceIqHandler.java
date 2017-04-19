package hu.unideb.smartcampus.service.api.iq.attendance;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.packet.IQ;

import hu.unideb.smartcampus.service.api.iq.IqHandler;
import hu.unideb.smartcampus.shared.iq.request.ChangeAttendanceIqRequest;

public class ChangeAttendanceIqHandler extends IqHandler<ChangeAttendanceIqRequest> {
  public ChangeAttendanceIqHandler(AbstractXMPPConnection connection, String domain, long appointmentId, boolean present) {
    this.connection = connection;
    this.domain = domain;
    this.iq = new ChangeAttendanceIqRequest();
    this.iq.setType(IQ.Type.get);
    this.iq.setFrom(connection.getUser());
    this.setSmartcampusUser(iq);
    this.iq.setStudent(getUser(connection));
    this.iq.setAppointmentId(appointmentId);
    this.iq.setPresent(present);
  }
}

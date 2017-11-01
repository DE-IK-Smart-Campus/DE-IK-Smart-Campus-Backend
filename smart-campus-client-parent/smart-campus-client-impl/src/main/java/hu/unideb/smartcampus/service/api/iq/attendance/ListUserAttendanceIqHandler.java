package hu.unideb.smartcampus.service.api.iq.attendance;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.packet.IQ;

import hu.unideb.smartcampus.service.api.iq.IqHandler;
import hu.unideb.smartcampus.shared.iq.request.ListUserAttendanceIqRequest;

public class ListUserAttendanceIqHandler extends IqHandler<ListUserAttendanceIqRequest> {
  public ListUserAttendanceIqHandler(AbstractXMPPConnection connection, String domain) {
    this.connection = connection;
    this.domain = domain;
    this.iq = new ListUserAttendanceIqRequest();
    this.iq.setType(IQ.Type.get);
    this.iq.setFrom(connection.getUser());
    this.setSmartcampusUser(iq);
    this.iq.setStudent(getUser(connection));
  }
}

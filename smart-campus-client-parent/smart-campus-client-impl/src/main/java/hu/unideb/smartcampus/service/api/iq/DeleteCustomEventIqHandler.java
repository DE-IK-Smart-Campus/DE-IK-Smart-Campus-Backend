package hu.unideb.smartcampus.service.api.iq;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.packet.IQ;

import hu.unideb.smartcampus.shared.iq.request.DeleteCustomEventIqRequest;

public class DeleteCustomEventIqHandler extends IqHandler<DeleteCustomEventIqRequest> {
  public DeleteCustomEventIqHandler(AbstractXMPPConnection connection, String domain, String eventGuid) {
    this.connection = connection;
    this.domain = domain;
    this.iq = new DeleteCustomEventIqRequest();
    this.iq.setType(IQ.Type.get);
    this.iq.setFrom(connection.getUser());
    this.setSmartcampusUser(iq);
    this.iq.setStudent(getUser(connection));
    this.iq.setEventGuid(eventGuid);
  }
}

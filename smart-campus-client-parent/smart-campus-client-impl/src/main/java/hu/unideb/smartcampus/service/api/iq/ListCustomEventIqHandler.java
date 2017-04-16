package hu.unideb.smartcampus.service.api.iq;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.packet.IQ;

import hu.unideb.smartcampus.shared.iq.request.ListCustomEventIqRequest;

public class ListCustomEventIqHandler extends IqHandler<ListCustomEventIqRequest> {
  public ListCustomEventIqHandler(AbstractXMPPConnection connection, String domain) {
    this.connection = connection;
    this.domain = domain;
    this.iq = new ListCustomEventIqRequest();
    this.iq.setType(IQ.Type.get);
    this.iq.setFrom(connection.getUser());
    this.setSmartcampusUser(iq);
    this.iq.setStudent(getUser(connection));
  }
}

package hu.unideb.smartcampus.service.api.iq;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.packet.IQ;

import hu.unideb.smartcampus.shared.iq.request.ListUserChatsIqRequest;

/**
 * IQ handler for IQ requests of subjects.
 */
public class MultiUserChatIqHandler extends IqHandler<ListUserChatsIqRequest> {
  public MultiUserChatIqHandler(AbstractXMPPConnection connection, String domain) {
    this.connection = connection;
    this.domain = domain;
    this.iq = new ListUserChatsIqRequest();
    this.iq.setType(IQ.Type.get);
    this.iq.setFrom(connection.getUser());
    this.setSmartcampusUser(iq);
    this.iq.setStudent(getUser(connection));
  }
}

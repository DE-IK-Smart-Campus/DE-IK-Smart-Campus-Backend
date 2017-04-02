package hu.unideb.smartcampus.service.api.iq;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.packet.IQ;

import hu.unideb.smartcampus.shared.iq.request.SubjectsIqRequest;

/**
 * IQ handler for IQ requests of subjects.
 */
public class SubjectsIqHandler extends IqHandler<SubjectsIqRequest> {
  public SubjectsIqHandler(AbstractXMPPConnection connection) {
    this.connection = connection;
    this.iq = new SubjectsIqRequest();
    this.iq.setType(IQ.Type.get);
    this.iq.setFrom(connection.getUser());
    this.setSmartcampusUser(iq);
    this.iq.setStudent(getUser(connection));
  }
}

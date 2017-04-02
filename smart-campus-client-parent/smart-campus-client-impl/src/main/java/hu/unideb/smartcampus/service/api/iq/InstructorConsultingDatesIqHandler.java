package hu.unideb.smartcampus.service.api.iq;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.packet.IQ;

import hu.unideb.smartcampus.shared.iq.request.InstructorConsultingDatesIqRequest;

/**
 * IQ handler for IQ requests of consulting dates of instructors.
 */
public class InstructorConsultingDatesIqHandler extends IqHandler<InstructorConsultingDatesIqRequest> {
  public InstructorConsultingDatesIqHandler(AbstractXMPPConnection connection, Long instructorId) {
    this.connection = connection;
    this.iq = new InstructorConsultingDatesIqRequest();
    this.iq.setType(IQ.Type.get);
    this.iq.setFrom(connection.getUser());
    this.setSmartcampusUser(iq);
    iq.setInstructorId(instructorId.toString());
  }
}

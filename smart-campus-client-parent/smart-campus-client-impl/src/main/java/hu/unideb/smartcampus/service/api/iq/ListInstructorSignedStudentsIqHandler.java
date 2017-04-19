package hu.unideb.smartcampus.service.api.iq;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.packet.IQ;

import hu.unideb.smartcampus.shared.iq.request.ListInstructorConsultingDatesIqRequest;

/**
 * IQ handler for IQ requests of signing up for a consulting date.
 */
public class ListInstructorSignedStudentsIqHandler extends IqHandler<ListInstructorConsultingDatesIqRequest> {
  public ListInstructorSignedStudentsIqHandler(AbstractXMPPConnection connection, String domain, String instructorNeptunIdentifier) {
    this.connection = connection;
    this.domain = domain;
    this.iq = new ListInstructorConsultingDatesIqRequest();
    this.iq.setType(IQ.Type.get);
    this.iq.setFrom(connection.getUser());
    this.setSmartcampusUser(iq);
    this.iq.setInstructorId(instructorNeptunIdentifier);
    this.iq.setOneWeek(true);
  }
}

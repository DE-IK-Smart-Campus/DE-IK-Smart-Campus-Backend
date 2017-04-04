package hu.unideb.smartcampus.service.api.iq;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.packet.IQ;

import hu.unideb.smartcampus.shared.iq.request.SignUpForConsultingDateIqRequest;

/**
 * IQ handler for IQ requests of signing up for a consulting date.
 */
public class SignUpForConsultingDateIqHandler extends IqHandler<SignUpForConsultingDateIqRequest> {
  public SignUpForConsultingDateIqHandler(AbstractXMPPConnection connection, String domain, Long consultingHourId, Long duration, String reason) {
    this.connection = connection;
    this.domain = domain;
    this.iq = new SignUpForConsultingDateIqRequest();
    this.iq.setType(IQ.Type.get);
    this.iq.setFrom(connection.getUser());
    this.setSmartcampusUser(iq);
    this.iq.setUserId(connection.getUser().asEntityBareJidString().split(AT)[0]);
    this.iq.setConsultingHourId(consultingHourId);
    this.iq.setDuration(duration.toString());
    this.iq.setReason(reason);
  }
}

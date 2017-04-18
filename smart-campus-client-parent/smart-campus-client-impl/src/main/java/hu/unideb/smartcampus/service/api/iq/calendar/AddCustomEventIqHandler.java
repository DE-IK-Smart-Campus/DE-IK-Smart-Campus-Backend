package hu.unideb.smartcampus.service.api.iq.calendar;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.packet.IQ;

import hu.unideb.smartcampus.service.api.iq.IqHandler;
import hu.unideb.smartcampus.shared.iq.request.AddCustomEventIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

public class AddCustomEventIqHandler extends IqHandler<AddCustomEventIqRequest> {
  public AddCustomEventIqHandler(AbstractXMPPConnection connection, String domain, CustomEventIqElement customEvent) {
    this.connection = connection;
    this.domain = domain;
    this.iq = new AddCustomEventIqRequest();
    this.iq.setType(IQ.Type.set);
    this.iq.setFrom(connection.getUser());
    this.setSmartcampusUser(iq);
    this.iq.setStudent(getUser(connection));
    this.iq.setCustomEvent(customEvent);
  }
}

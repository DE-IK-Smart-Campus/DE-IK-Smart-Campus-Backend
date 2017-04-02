package hu.unideb.smartcampus.service.api.xmpp.handler.event.custom;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.CustomEventService;
import hu.unideb.smartcampus.service.api.xmpp.handler.AbstractSmartCampusIqRequestHandler;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.DeleteCustomEventIqRequest;

/**
 * Delete custom event handler.
 */
@Service
public class DeleteCustomEventIqRequestHandler extends AbstractSmartCampusIqRequestHandler {

  @Autowired
  private CustomEventService customEventService;

  /**
   * Ctor.
   */
  public DeleteCustomEventIqRequestHandler() {
    super(DeleteCustomEventIqRequest.ELEMENT, BaseSmartCampusIqRequest.BASE_NAMESPACE, Type.set,
        Mode.async);
  }

  /**
   * Ctor.
   */
  protected DeleteCustomEventIqRequestHandler(String element, String namespace, Type type,
      Mode mode) {
    super(element, namespace, type, mode);
  }

  /**
   * Handling request.
   */
  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    DeleteCustomEventIqRequest iq = (DeleteCustomEventIqRequest) super.handleIQRequest(iqRequest);
    customEventService.deleteCustomEntityByIq(iq);
    return iq;
  }

}

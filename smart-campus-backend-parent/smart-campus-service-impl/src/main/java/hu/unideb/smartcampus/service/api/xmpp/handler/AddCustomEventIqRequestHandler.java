package hu.unideb.smartcampus.service.api.xmpp.handler;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.CustomEventService;
import hu.unideb.smartcampus.shared.iq.request.AddCustomEventIqRequest;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;

/**
 * Add custom event handler.
 */
@Service
public class AddCustomEventIqRequestHandler extends AbstractSmartCampusIqRequestHandler {

  @Autowired
  private CustomEventService customEventService;

  /**
   * Ctor.
   */
  public AddCustomEventIqRequestHandler() {
    super(AddCustomEventIqRequest.ELEMENT, BaseSmartCampusIqRequest.BASE_NAMESPACE, Type.set,
        Mode.async);
  }

  /**
   * Ctor.
   */
  protected AddCustomEventIqRequestHandler(String element, String namespace, Type type,
      Mode mode) {
    super(element, namespace, type, mode);
  }

  /**
   * Handling request.
   */
  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    AddCustomEventIqRequest iq = (AddCustomEventIqRequest) super.handleIQRequest(iqRequest);
    customEventService.addCustomEntityByIq(iq);
    return iq;
  }

}

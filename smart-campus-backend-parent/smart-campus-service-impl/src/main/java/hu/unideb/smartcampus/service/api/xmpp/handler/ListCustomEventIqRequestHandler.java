package hu.unideb.smartcampus.service.api.xmpp.handler;

import java.util.List;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.CustomEventService;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListCustomEventIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

/**
 * Calendar subject retrieval handler.
 */
@Service
public class ListCustomEventIqRequestHandler extends AbstractSmartCampusIqRequestHandler {

  @Autowired
  private CustomEventService customEventService;

  /**
   * Ctor.
   */
  public ListCustomEventIqRequestHandler() {
    super(ListCustomEventIqRequest.ELEMENT, BaseSmartCampusIqRequest.BASE_NAMESPACE, Type.get,
        Mode.async);
  }

  /**
   * Ctor.
   */
  protected ListCustomEventIqRequestHandler(String element, String namespace, Type type,
      Mode mode) {
    super(element, namespace, type, mode);
  }

  /**
   * Handling request.
   */
  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    ListCustomEventIqRequest iq = (ListCustomEventIqRequest) super.handleIQRequest(iqRequest);
    List<CustomEventIqElement> customEvents = customEventService.getCustomEventsByIq(iq);
    iq.setCustomEvents(customEvents);
    return iq;
  }

}

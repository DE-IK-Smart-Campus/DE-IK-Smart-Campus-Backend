package hu.unideb.smartcampus.service.api.xmpp.handler;

import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;


/**
 * Abstract smart campus IQ request handler.
 */
public abstract class AbstractSmartCampusIqRequestHandler extends AbstractIqRequestHandler {

  /**
   * Ctor.
   */
  public AbstractSmartCampusIqRequestHandler(String element, String namespace, Type type,
      Mode mode) {
    super(element, namespace, type, mode);
  }

  /**
   * Handling request.
   */
  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    return makeItResult(iqRequest);
  }

  private IQ makeItResult(final IQ iqRequest) {
    IQ result = iqRequest;
    result.setType(Type.result);
    result.setTo(iqRequest.getFrom());
    result.setFrom(iqRequest.getTo());
    return result;
  }


}

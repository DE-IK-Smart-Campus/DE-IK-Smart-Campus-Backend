package hu.unideb.smartcampus.service.api.xmpp.handler;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.shared.iq.request.TestIq;


/**
 * Subject retrieval.
 */
@Service
public class TestIqHandler extends AbstractSmartCampusIqRequestHandler {

  /**
   * Ctor.
   */
  public TestIqHandler() {
    super(TestIq.ELEMENT, TestIq.NAMESPACE, Type.get, Mode.async);
  }

  /**
   * Ctor.
   */
  protected TestIqHandler(String element, String namespace, Type type, Mode mode) {
    super(element, namespace, type, mode);
  }

  /**
   * Handling request.
   */
  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    TestIq iq = (TestIq) super.handleIQRequest(iqRequest);
    iq.setStudent("kurva anyadat.");
    return iq;
  }


}

package hu.unideb.smartcampus.service.api.xmpp.handler.chat;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.UserChatService;
import hu.unideb.smartcampus.service.api.xmpp.handler.AbstractSmartCampusIqRequestHandler;
import hu.unideb.smartcampus.shared.iq.request.AddMucChatIqRequest;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;

/**
 * Add MUC to user.
 */
@Service
public class AddUserMucIqRequestHandler extends AbstractSmartCampusIqRequestHandler {

  @Autowired
  private UserChatService userChatService;

  /**
   * Ctor.
   */
  public AddUserMucIqRequestHandler() {
    super(AddMucChatIqRequest.ELEMENT, BaseSmartCampusIqRequest.BASE_NAMESPACE, Type.set,
        Mode.async);
  }

  /**
   * Ctor.
   */
  protected AddUserMucIqRequestHandler(String element, String namespace, Type type,
      Mode mode) {
    super(element, namespace, type, mode);
  }

  /**
   * Handling request.
   */
  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    AddMucChatIqRequest iq = (AddMucChatIqRequest) super.handleIQRequest(iqRequest);
    userChatService.addMucToUser(iq.getStudent(), iq.getMuc());
    return iq;
  }

}

package hu.unideb.smartcampus.service.api.xmpp.handler.chat;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.UserChatService;
import hu.unideb.smartcampus.service.api.xmpp.handler.AbstractSmartCampusIqRequestHandler;
import hu.unideb.smartcampus.shared.iq.request.AddUserChatIqRequest;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;

/**
 * Add partner to user.
 */
@Service
public class AddUserChatIqRequestHandler extends AbstractSmartCampusIqRequestHandler {

  @Autowired
  private UserChatService userChatService;

  /**
   * Ctor.
   */
  public AddUserChatIqRequestHandler() {
    super(AddUserChatIqRequest.ELEMENT, BaseSmartCampusIqRequest.BASE_NAMESPACE, Type.set,
        Mode.async);
  }

  /**
   * Ctor.
   */
  protected AddUserChatIqRequestHandler(String element, String namespace, Type type,
      Mode mode) {
    super(element, namespace, type, mode);
  }

  /**
   * Handling request.
   */
  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    AddUserChatIqRequest iq = (AddUserChatIqRequest) super.handleIQRequest(iqRequest);
    userChatService.addChatToUser(iq.getStudent(), iq.getChat());
    return iq;
  }

}

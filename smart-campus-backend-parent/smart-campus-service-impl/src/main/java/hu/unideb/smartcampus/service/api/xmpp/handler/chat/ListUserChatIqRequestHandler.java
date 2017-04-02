package hu.unideb.smartcampus.service.api.xmpp.handler.chat;

import java.util.List;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.UserChatService;
import hu.unideb.smartcampus.service.api.xmpp.handler.AbstractSmartCampusIqRequestHandler;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListUserChatIqRequest;

/**
 * List user chat clients.
 */
@Service
public class ListUserChatIqRequestHandler extends AbstractSmartCampusIqRequestHandler {

  @Autowired
  private UserChatService userChatService;
  
  /**
   * Ctor.
   */
  public ListUserChatIqRequestHandler() {
    super(ListUserChatIqRequest.ELEMENT, BaseSmartCampusIqRequest.BASE_NAMESPACE, Type.get,
        Mode.async);
  }

  /**
   * Ctor.
   */
  protected ListUserChatIqRequestHandler(String element, String namespace, Type type,
      Mode mode) {
    super(element, namespace, type, mode);
  }

  /**
   * Handling request.
   */
  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    ListUserChatIqRequest iq = (ListUserChatIqRequest) super.handleIQRequest(iqRequest);
    List<String> chatList = userChatService.listUserChats(iq.getStudent());
    iq.setChatList(chatList);
    return iq;
  }

}

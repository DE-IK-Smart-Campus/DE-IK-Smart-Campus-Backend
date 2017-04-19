package hu.unideb.smartcampus.service.api.xmpp.handler.chat;

import java.util.List;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.UserChatService;
import hu.unideb.smartcampus.service.api.xmpp.handler.AbstractSmartCampusIqRequestHandler;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListUserChatsIqRequest;

/**
 * List user chat clients.
 */
@Service
public class ListUserChatIqRequestHandler extends AbstractSmartCampusIqRequestHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ListUserChatIqRequestHandler.class);
  
  @Autowired
  private UserChatService userChatService;

  /**
   * Ctor.
   */
  public ListUserChatIqRequestHandler() {
    super(ListUserChatsIqRequest.ELEMENT, BaseSmartCampusIqRequest.BASE_NAMESPACE, Type.get,
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
    LOGGER.debug("ListUserChatIqRequestHandler#handleIQRequest()");
    ListUserChatsIqRequest iq = (ListUserChatsIqRequest) super.handleIQRequest(iqRequest);
    List<String> chatList = userChatService.listUserChats(iq.getStudent());
    List<String> mucChatList = userChatService.listUserMucRooms(iq.getStudent());
    iq.setChatList(chatList);
    iq.setMucChatList(mucChatList);
    return iq;
  }

}

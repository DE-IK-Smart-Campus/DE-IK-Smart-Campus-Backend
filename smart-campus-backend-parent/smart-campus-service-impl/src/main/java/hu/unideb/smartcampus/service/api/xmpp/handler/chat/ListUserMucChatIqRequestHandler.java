package hu.unideb.smartcampus.service.api.xmpp.handler.chat;

import java.util.List;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.UserChatService;
import hu.unideb.smartcampus.service.api.xmpp.handler.AbstractSmartCampusIqRequestHandler;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListUserMucIqRequest;

/**
 * List user MUC rooms.
 */
@Service
public class ListUserMucChatIqRequestHandler extends AbstractSmartCampusIqRequestHandler {

  @Autowired
  private UserChatService userChatService;

  /**
   * Ctor.
   */
  public ListUserMucChatIqRequestHandler() {
    super(ListUserMucIqRequest.ELEMENT, BaseSmartCampusIqRequest.BASE_NAMESPACE, Type.get,
        Mode.async);
  }

  /**
   * Ctor.
   */
  protected ListUserMucChatIqRequestHandler(String element, String namespace, Type type,
      Mode mode) {
    super(element, namespace, type, mode);
  }

  /**
   * Handling request.
   */
  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    ListUserMucIqRequest iq = (ListUserMucIqRequest) super.handleIQRequest(iqRequest);
    List<String> mucChatList = userChatService.listUserMucRooms(iq.getStudent());
    iq.setMucChatList(mucChatList);
    return iq;
  }

}

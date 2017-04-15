package hu.unideb.smartcampus.service.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.domain.MucRoom;
import hu.unideb.smartcampus.service.api.iq.MultiUserChatIqHandler;
import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.iq.request.ListUserChatsIqRequest;

/**
 * Service for multi user chat.
 */
@Service
public class MultiUserChatServiceImpl implements MultiUserChatService {

  /**
   * Ejabberd user.
   */
  @Autowired
  private EjabberdUser ejabberdUser;

  /**
   * Domain for smartcampus user.
   */
  @Resource(lookup = "java:global/smartcampus.xmpp.domain")
  private String domain;

  @Override
  public List<MucRoom> getMucRooms() {
    AbstractXMPPConnection connection = ejabberdUser.getConnection();
    // TODO : Use vCard to get nickname.
    String nick = connection.getUser().toString();
    final MultiUserChatIqHandler handler =
        new MultiUserChatIqHandler(connection, domain);
    ListUserChatsIqRequest result = handler.getResult();
    List<String> mucChatList = result.getMucChatList();
    return mucChatList
        .stream()
        .map(chat -> toMucRoom(chat, nick))
        .collect(Collectors.toList());
  }

  private MucRoom toMucRoom(String jid, String nick) {
    return MucRoom.builder()
        .jid(jid)
        .nick(nick)
        .build();
  }

}

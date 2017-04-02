package hu.unideb.smartcampus.service.api.xmpp;

import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaCollector;
import org.jxmpp.jid.Jid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.shared.exception.InvalidXmppConnectionException;
import hu.unideb.smartcampus.shared.exception.XmppException;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;

@Service
public class XmppConnectedServiceImpl implements XmppConnectedService {

  private static final long XMPP_TIMEOUT = 50000;

  protected static final String SMARTCAMPUS_DEFAULT_USER_JID =
      "smartcampus@smartcampus/Smartcampus";

  @Autowired
  private EjabberdUser ejabberdUser;

  @Override
  public <T extends BaseSmartCampusIqRequest> T sendRequest(T iq)
      throws XmppException, InvalidXmppConnectionException {
    try {
      
      iq.setFrom(createUserJid());
      iq.setTo(createSmartCampusDefaultUserJid());
      
      StanzaCollector collector = ejabberdUser.getConnection().createStanzaCollectorAndSend(iq);
      T resultIq = collector.nextResult(XMPP_TIMEOUT);
      return resultIq;
    } catch (InterruptedException e) {
      throw new XmppException(e);
    } catch (NotConnectedException e) { 
      throw new InvalidXmppConnectionException(e);
    }
  }

  @Override
  public <T extends BaseSmartCampusIqRequest> T sendRequestWithReauthenticate(T iq)
      throws XmppException, InvalidXmppConnectionException {

    try {
      return sendRequest(iq);
    } catch (InvalidXmppConnectionException e) {
      ejabberdUser.reauthenticate();
      return sendRequest(iq);
    }

  }
  
  protected Jid createUserJid(){
    return ejabberdUser.getConnection().getUser();
  }

  protected Jid createJid(String jabberId) {
    try {
      return JidCreate.from(jabberId);
    } catch (XmppStringprepException e) {
      throw new IllegalArgumentException(e);
    }
  }

  protected Jid createSmartCampusDefaultUserJid() {
    return createJid(SMARTCAMPUS_DEFAULT_USER_JID);
  }

}
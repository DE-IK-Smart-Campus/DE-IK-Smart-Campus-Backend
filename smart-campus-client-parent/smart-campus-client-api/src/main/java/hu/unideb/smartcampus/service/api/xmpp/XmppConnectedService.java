package hu.unideb.smartcampus.service.api.xmpp;

import hu.unideb.smartcampus.shared.exception.InvalidXmppConnectionException;
import hu.unideb.smartcampus.shared.exception.XmppException;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;


public interface XmppConnectedService {

  <T extends BaseSmartCampusIqRequest> T sendRequest(T iq)
      throws XmppException, InvalidXmppConnectionException;

  <T extends BaseSmartCampusIqRequest> T sendRequestWithReauthenticate(T iq)
      throws XmppException, InvalidXmppConnectionException;

}
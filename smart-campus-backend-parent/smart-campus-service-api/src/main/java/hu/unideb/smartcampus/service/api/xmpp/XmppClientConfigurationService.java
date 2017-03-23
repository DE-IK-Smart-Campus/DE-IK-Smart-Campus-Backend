package hu.unideb.smartcampus.service.api.xmpp;

import org.jivesoftware.smack.bosh.BOSHConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import hu.unideb.smartcampus.shared.exception.XmppException;

/**
 * XMPP client configuration service.
 *
 */
public interface XmppClientConfigurationService {
  /**
   * Creates XMPP BOSH client configuration.
   *
   * @param username username.
   * @param password password
   * @return XMPPTCPConnection configuration.
   */
  BOSHConfiguration getBoshConfigurationByUserNameAndPassword(String username, String password)
      throws XmppException;

  /**
   * Creates XMPP TCP client configuration.
   *
   * @param username username.
   * @param password password
   * @return XMPPTCPConnection configuration.
   */
  XMPPTCPConnectionConfiguration getXmppConfigurationByUserNameAndPassword(String username,
      String password) throws XmppException;
}

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

  /**
   * Creates XMPP TCP client configuration.
   *
   * @param username username.
   * @param password password
   * @param resource resource.
   * @return XMPPTCPConnection configuration.
   */
  XMPPTCPConnectionConfiguration getXmppConfigurationByUserNameAndPasswordAndResource(
      String username,
      String password, String resource) throws XmppException;
  
  /**
   * Creates XMPP TCP client configuration.
   *
   * @param username username.
   * @param password password
   * @param resource resource.
   * @param sendPresence sending presence or not.
   * @return XMPPTCPConnection configuration.
   */
  XMPPTCPConnectionConfiguration getXmppConfigurationByUserNameAndPasswordAndResource(
      String username,
      String password, String resource, boolean sendPresence) throws XmppException;
}

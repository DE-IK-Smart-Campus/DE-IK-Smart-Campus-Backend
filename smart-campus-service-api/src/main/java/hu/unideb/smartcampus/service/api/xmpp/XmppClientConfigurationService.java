package hu.unideb.smartcampus.service.api.xmpp;

import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

/**
 * XMPP TCP client configuration service.
 *
 */
public interface XmppClientConfigurationService {
  /**
   * Creates XMPP TCP client configuration.
   *
   * @param username username.
   * @param password password
   * @return XMPPTCPConnection configuration.
   */
  XMPPTCPConnectionConfiguration getConfigurationByUsernameAndPassword(String username,
      String password);
}

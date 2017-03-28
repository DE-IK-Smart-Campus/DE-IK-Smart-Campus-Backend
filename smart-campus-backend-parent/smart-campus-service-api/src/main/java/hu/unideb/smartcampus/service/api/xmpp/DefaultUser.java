package hu.unideb.smartcampus.service.api.xmpp;

import org.jivesoftware.smack.tcp.XMPPTCPConnection;

/**
 * Default (smartcampus) user interface.
 *
 */
public interface DefaultUser {

  /**
   * Get default smartcampus user's connection.
   *
   * @return default smartcampus user's active connection.
   */
  XMPPTCPConnection getConnection();

  /**
   * Reconnect.
   */
  void reconnect();

  /**
   * Checks if the default user is connected to the XMPP server.
   *
   * @return {@code true} if the default user is connected to the XMPP server {@code false}
   *         otherwise
   */
  boolean isDisconnected();

}

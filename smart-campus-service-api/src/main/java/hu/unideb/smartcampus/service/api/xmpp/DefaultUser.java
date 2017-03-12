package hu.unideb.smartcampus.service.api.xmpp;

import org.jivesoftware.smack.tcp.XMPPTCPConnection;

/**
 * Default (smartcampus) user interface.
 *
 */
public interface DefaultUser {

  /**
   * Default user receive message.s
   *
   * @param message the received message.
   */
  void receiveMessage(String message);

  /**
   * Get default smartcampus user's connection.
   *
   * @return default smartcampus user's active connection.
   */
  XMPPTCPConnection getConnection();

}

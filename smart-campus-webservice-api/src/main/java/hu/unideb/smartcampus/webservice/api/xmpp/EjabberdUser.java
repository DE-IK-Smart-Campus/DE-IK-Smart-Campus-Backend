package hu.unideb.smartcampus.webservice.api.xmpp;

import org.jivesoftware.smack.tcp.XMPPTCPConnection;

import hu.unideb.smartcampus.shared.exception.XmppException;

/**
 * Ejabberd user client interface.
 *
 */
public interface EjabberdUser {

  /**
   * Login user with credentials.
   *
   * @param username username.
   * @param password password.
   * @throws XmppException thrown when SmackException occoured.
   */
  void login(String username, String password) throws XmppException;

  /**
   * Logout user.
   */
  void logout();

  /**
   * Returns the XMPP connection to work with.
   *
   * @return provided XMPP connection.
   */
  XMPPTCPConnection getConnection();

}

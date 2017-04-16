package hu.unideb.smartcampus.service.api.xmpp;

import org.jivesoftware.smack.AbstractXMPPConnection;

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
   * Login user with credentials.
   *
   * @param username username.
   * @param password password.
   * @param resource resource.
   * @throws XmppException thrown when SmackException occoured.
   */
  void login(String username, String password, String resource) throws XmppException;
  
  /**
   * Login user with credentials.
   *
   * @param username username.
   * @param password password.
   * @param resource resource.
   * @param sendPresence send presence or not.
   * @throws XmppException thrown when SmackException occoured.
   */
  void login(String username, String password, String resource,boolean sendPresence) throws XmppException;

  /**
   * Logout user.
   */
  void logout();

  /**
   * Returns the XMPP connection to work with.
   *
   * @return provided XMPP connection.
   */
  AbstractXMPPConnection getConnection();

}

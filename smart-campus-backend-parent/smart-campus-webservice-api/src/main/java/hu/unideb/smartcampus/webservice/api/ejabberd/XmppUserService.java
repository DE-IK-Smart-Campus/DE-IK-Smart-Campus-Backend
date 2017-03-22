package hu.unideb.smartcampus.webservice.api.ejabberd;

import hu.unideb.smartcampus.shared.exception.XmppException;

/**
 * Handling user operations of the REST based API calls.
 *
 */
public interface XmppUserService {

  /**
   * Creates a user at the XMPP server.
   * 
   * @param username the username of the user
   * @param password the password of the user
   * @throws XmppException thrown if the XMPP server not responded as expected
   */
  void createUser(String username, String password) throws XmppException;
  
  
  /**
   *  Checks if a user exists at the XMPP server.
   * 
   * @param username the username of the user
   * @return {@code true} if the user exists {@false otherwise}
   * @throws XmppException if the XMPP server not responded as expected
   */
  boolean userExists(String username)throws XmppException;

  /**
   * Changes the password of a user.
   * 
   * @param username the username of the user
   * @param password the new password of the user
   * @throws XmppException if the XMPP server not responded as expected
   */
  void changePassword(String username,String password) throws XmppException;
}

package hu.unideb.smartcampus.service.api.xmpp;

import java.io.IOException;

import javax.annotation.PreDestroy;

import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.shared.exception.XmppException;
import hu.unideb.smartcampus.shared.exception.ConnectionException;
import hu.unideb.smartcampus.shared.exception.LoginException;

/**
 * Session scoped service for XMPP connection.
 *
 * <p>
 * This "service" should be used to control the user on the XMPP server and our client, you can use
 * the login and logout function to login the user and on the end of the session you can log out
 * user too.
 * </p>
 */
@Service
@Scope(scopeName = EjabberdUserImpl.BEAN_SCOPE, proxyMode = ScopedProxyMode.INTERFACES)
public class EjabberdUserImpl implements EjabberdUser {

  public static final String BEAN_SCOPE = "session";

  private static final Logger LOGGER = LoggerFactory.getLogger(EjabberdUserImpl.class);

  private static final String SMARTCAMPUS = "smartcampus";

  /**
   * XMPP connection to Ejabberd server.
   */
  private XMPPTCPConnection connection;

  @PreDestroy
  public void preDestroy() {
    LOGGER.info("Logging out user on session destroy.");
    logout();
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void login(String username, String password) throws XmppException {
    LOGGER.info("Logging in user {}", username);
    if (connection == null) {
      initConnection(username, password);
    }
    LOGGER.info("Login succesfull.");
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void logout() {
    LOGGER.info("Logging out user.");
    if (connection != null) {
      disconnectAndClearConnection();
    }
    LOGGER.info("Logout was succesfull.");
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public XMPPTCPConnection getConnection() {
    return connection;
  }

  private void disconnectAndClearConnection() {
    connection.disconnect();
    connection = null;
  }

  private void initConnection(String username, String password) throws XmppException {
    XMPPTCPConnectionConfiguration conf = getConfigurationByUsernameAndPassword(username, password);
    connection = new XMPPTCPConnection(conf);
    connect();
    doLogin();
  }

  private XMPPTCPConnectionConfiguration getConfigurationByUsernameAndPassword(String username,
      String password) {
    return XMPPTCPConnectionConfiguration.builder().setHost(SMARTCAMPUS).setServiceName(SMARTCAMPUS)
        .setPort(5222).setSecurityMode(SecurityMode.disabled).setDebuggerEnabled(true)
        .setUsernameAndPassword(username, password).build();
  }

  private void connect() throws ConnectionException {
    try {
      connection.connect();
    } catch (SmackException | IOException | XMPPException e) {
      LOGGER.error("Connection could not been established.", e);
      throw new ConnectionException("Error on connection to Ejabberd server.", e);
    }
  }

  private void doLogin() throws LoginException {
    try {
      connection.login();
    } catch (XMPPException | SmackException | IOException e) {
      LOGGER.error("User could not log in.", e);
      throw new LoginException("Error on logging in to Ejabberd server.", e);
    }
  }



}

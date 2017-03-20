package hu.unideb.smartcampus.service.api.xmpp.impl;

import java.io.IOException;

import javax.annotation.PreDestroy;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.bosh.BOSHConfiguration;
import org.jivesoftware.smack.bosh.XMPPBOSHConnection;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.service.api.xmpp.SubjectsManager;
import hu.unideb.smartcampus.service.api.xmpp.SubjectsManagerFactory;
import hu.unideb.smartcampus.service.api.xmpp.TestIqManager;
import hu.unideb.smartcampus.service.api.xmpp.XmppClientConfigurationService;
import hu.unideb.smartcampus.shared.exception.ConnectionException;
import hu.unideb.smartcampus.shared.exception.LoginException;
import hu.unideb.smartcampus.shared.exception.XmppException;
import hu.unideb.smartcampus.shared.iq.SubjectsIq;

/**
 * Session scoped service for XMPP connection.
 *
 * <p>
 * This "service" should be used to control the user on the XMPP server and our client, you can use
 * the login and logout function to login the user and on the end of the session you can log out
 * user too.
 * </p>
 */
@Component
@Scope(scopeName = EjabberdUserImpl.BEAN_SCOPE, proxyMode = ScopedProxyMode.INTERFACES)
public class EjabberdUserImpl implements EjabberdUser {

  public static final String BEAN_SCOPE = "session";

  private static final Logger LOGGER = LoggerFactory.getLogger(EjabberdUserImpl.class);

  /**
   * XMPP connection to Ejabberd server.
   */
  private XMPPBOSHConnection connection;

  @Autowired
  private XmppClientConfigurationService connectionConfigurationService;

  private Object testIqManager;

  @Autowired
  private SubjectsManagerFactory subjectsManagerFactory;

  private SubjectsManager subjectsManager;

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
      initIqHandler();
    }
    LOGGER.info("Login succesfull.");
  }

  private void initIqHandler() {
    testIqManager = TestIqManager.getInstanceFor(connection);
    subjectsManager = subjectsManagerFactory.registerConnectionSubjectManager(connection);
    LOGGER.info("Subject manager:{}",subjectsManager);
    LOGGER.info("{}", testIqManager.toString());
    connection.addSyncStanzaListener(new StanzaListener() {

      @Override
      public void processPacket(Stanza packet) throws NotConnectedException {
        SubjectsIq iq = (SubjectsIq) packet;
        LOGGER.info("IQ:", iq);
      }
    }, new StanzaFilter() {

      @Override
      public boolean accept(Stanza stanza) {
        return stanza instanceof SubjectsIq;
      }
    });
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
  public AbstractXMPPConnection getConnection() {
    return connection;
  }

  private void disconnectAndClearConnection() {
    connection.disconnect();
    connection = null;
  }

  private void initConnection(String username, String password) throws XmppException {
    BOSHConfiguration conf = connectionConfigurationService
        .getBoshConfigurationByUserNameAndPassword(username, password);
    connection = new XMPPBOSHConnection(conf);
    connect();
    doLogin();
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

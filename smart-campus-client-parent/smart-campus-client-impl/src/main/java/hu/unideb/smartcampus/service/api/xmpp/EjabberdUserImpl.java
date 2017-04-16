package hu.unideb.smartcampus.service.api.xmpp;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.PreDestroy;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.bosh.BOSHConfiguration;
import org.jivesoftware.smack.bosh.XMPPBOSHConnection;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Type;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.service.api.authentication.SmartCampusUserDetails;
import hu.unideb.smartcampus.shared.exception.ConnectionException;
import hu.unideb.smartcampus.shared.exception.IqRegistrationException;
import hu.unideb.smartcampus.shared.exception.LoginException;
import hu.unideb.smartcampus.shared.exception.XmppException;


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

  @Autowired
  private IqRegistrationService registartionService;

  private Roster roster;

  /**
   * {@inheritDoc}.
   */
  @Override
  public AbstractXMPPConnection getConnection() {
    return connection;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void login(String username, String password) throws XmppException {
    LOGGER.info("Logging in user {}", username);
    if (connection == null || !connection.isAuthenticated()) {
      initConnection(username, password);
      roster = Roster.getInstanceFor(connection);
      Collection<RosterGroup> groups = roster.getGroups();
      for (RosterGroup rosterGroup : groups) {
        LOGGER.info("Groups: {}", rosterGroup.getName());
      }
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
      sendUnavailablePresence();
      disconnectAndClearConnection();
    }
    LOGGER.info("Logout was succesfull.");
  }

  @PreDestroy
  public void preDestroy() {
    LOGGER.info("Logging out user on session destroy.");
    logout();
  }

  @Override
  public void reauthenticate() throws XmppException {
    SmartCampusUserDetails authentication = getAuthentication();
    login(authentication.getUsername(), authentication.getPassword());
  }

  private void connect() throws ConnectionException {
    try {
      connection.connect();
      LOGGER.info("Connected:{}", connection.isConnected());
    } catch (SmackException | IOException | XMPPException | InterruptedException e) {
      resetConnection();
      LOGGER.error("Connection could not been established.", e);
      throw new ConnectionException("Error on connection to Ejabberd server.", e);
    }
  }

  private void disconnectAndClearConnection() {
    connection.disconnect();
    resetConnection();
  }

  private void doLogin() throws LoginException {
    try {
      connection.login();
      sendSmartCampusPresence();
    } catch (XMPPException | SmackException | IOException | InterruptedException e) {
      resetConnection();
      LOGGER.error("User could not log in.", e);
      throw new LoginException("Error on logging in to Ejabberd server.", e);
    }
  }

  private SmartCampusUserDetails getAuthentication() {
    return (SmartCampusUserDetails) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
  }

  private void initConnection(String username, String password) throws XmppException {
    final BOSHConfiguration conf = connectionConfigurationService
        .getBoshConfigurationByUserNameAndPassword(username, password);
    connection = new XMPPBOSHConnection(conf);
    connect();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      LOGGER.error("Thread sleeping was not working.");
    }
    doLogin();
    initIqProviders();
  }

  private void initIqProviders() {
    try {
      registartionService.registerIqWithProviders();
    } catch (IqRegistrationException e) {
      LOGGER.error("Unable to register IQ provider.");
    }
  }

  private void resetConnection() {
    connection = null;
  }

  private void sendSmartCampusPresence() throws NotConnectedException, InterruptedException {
    connection.sendStanza(new Presence(Type.probe));
  }

  private void sendUnavailablePresence() {
    try {
      connection.sendStanza(new Presence(Type.unavailable));
    } catch (NotConnectedException | InterruptedException e) {
      LOGGER.error("Error on setting presence to unavailable.", e);
    }
  }

}

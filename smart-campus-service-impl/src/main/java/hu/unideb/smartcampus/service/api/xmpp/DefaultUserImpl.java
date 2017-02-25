package hu.unideb.smartcampus.service.api.xmpp;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.shared.exception.ConnectionException;
import hu.unideb.smartcampus.shared.exception.LoginException;
import hu.unideb.smartcampus.shared.exception.XmppException;

@Component
@Scope(DefaultUserImpl.BEAN_SCOPE)
public class DefaultUserImpl implements DefaultUser {

  public static final String BEAN_SCOPE = "singleton";

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUserImpl.class);

  /**
   * Default smart campus user's username.
   */
  @Value("${smartcampus.default.user}")
  private String user;

  /**
   * Default smart campus user's password.
   */
  @Value("${smartcampus.default.password}")
  private String password;

  /**
   * XMPP connection for default smart campus user.
   */
  private XMPPTCPConnection connection;

  @Autowired
  private XmppClientConfigurationService connectionConfigurationService;

  /**
   * Init after class.
   */
  @PostConstruct
  public void init() {
    LOGGER.info("Starting default smart campus user...");
    try {
      initConnection(user, password);
      registerDefaultListener();
      LOGGER.info("Default user is ready to accept messages.");
    } catch (XmppException e) {
      LOGGER.error("Error while logging in default user.", e);
    }
  }


  private void registerDefaultListener() {
    connection.addAsyncStanzaListener(
        packet -> LOGGER.info("Incoming Async stanza:{}", packet.toXML()), stanza -> true);
  }

  private void initConnection(String username, String password) throws XmppException {
    XMPPTCPConnectionConfiguration conf =
        connectionConfigurationService.getConfigurationByUsernameAndPassword(username, password);
    connection = new XMPPTCPConnection(conf);
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

  /**
   * Receive message.
   */
  @Override
  public void receiveMessage(String message) {
    throw new UnsupportedOperationException("Empty method yet.");
  }


  @Override
  public XMPPTCPConnection getConnection() {
    return connection;
  }

}

package hu.unideb.smartcampus.service.api.xmpp.impl;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.service.api.filter.TestIqFilter;
import hu.unideb.smartcampus.service.api.iqprovider.SubjectIqProvider;
import hu.unideb.smartcampus.service.api.iqprovider.TestIqProvider;
import hu.unideb.smartcampus.service.api.xmpp.DefaultUser;
import hu.unideb.smartcampus.service.api.xmpp.TestIqManager;
import hu.unideb.smartcampus.service.api.xmpp.XmppClientConfigurationService;
import hu.unideb.smartcampus.shared.exception.ConnectionException;
import hu.unideb.smartcampus.shared.exception.LoginException;
import hu.unideb.smartcampus.shared.exception.XmppException;
import hu.unideb.smartcampus.shared.iq.SubjectsIq;
import hu.unideb.smartcampus.shared.iq.TestIq;
import hu.unideb.smartcampus.shared.iq.TestIq.Thing;

/**
 * Default user implementation, smartcampus@HOST.
 *
 */
@Component
@Scope(DefaultUserImpl.BEAN_SCOPE)
@SuppressWarnings({"PMD.ExcessiveImports"})
public class DefaultUserImpl implements DefaultUser {

  public static final String BEAN_SCOPE = "singleton";

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUserImpl.class);

  /**
   * Default smart campus user's username.
   */
  @Resource(lookup = "java:global/smartcampus.default.user")
  private String user;

  /**
   * Default smart campus user's password.
   */
  @Resource(lookup = "java:global/smartcampus.default.password")
  private String password;

  /**
   * XMPP connection for default smart campus user.
   */
  private XMPPTCPConnection connection;

  /**
   * XMPP server chat manager for default user.
   */
  private ChatManager chatManager;

  @Autowired
  private XmppClientConfigurationService connectionConfigurationService;

  @Autowired
  private ChatManagerListener chatManagerListener;

  private TestIqManager testIqManager;

  /**
   * Init after class.
   */
  @PostConstruct
  public void init() {
    LOGGER.info("Starting default smart campus user...");
    try {
      initConnection(user, password);
      registerDefaultListener();
      registerCustomIQs();
      LOGGER.info("Default user is ready to accept messages.");
    } catch (XmppException e) {
      LOGGER.error("Error while logging in default user.", e);
    }
  }


  private void registerCustomIQs() {
    ProviderManager.addIQProvider("test", "http://inf.unideb.hu/smartcampus/test",
        new TestIqProvider());
    ProviderManager.addIQProvider(SubjectsIq.ELEMENT, SubjectsIq.NAMESPACE,
        new SubjectIqProvider());
    testIqManager = TestIqManager.getInstanceFor(connection);
    LOGGER.info("{}", testIqManager.toString());
  }


  private void registerDefaultListener() {
    TestIq reply = new TestIq("IamSmartCampus", Arrays.asList(new Thing("hopsz", "Thingy")));
    connection.addSyncStanzaListener(new StanzaListener() {
      @Override
      public void processPacket(Stanza packet) throws NotConnectedException {
        LOGGER.info("Get a TestIQ.");
        reply.setType(IQ.Type.result);
        reply.setStanzaId(packet.getStanzaId());
        reply.setFrom(packet.getTo());
        reply.setTo(packet.getFrom());
      }
    }, new TestIqFilter());
  }

  private void initConnection(String username, String password) throws XmppException {
    XMPPTCPConnectionConfiguration conf = connectionConfigurationService
        .getXmppConfigurationByUserNameAndPassword(username, password);
    connection = new XMPPTCPConnection(conf);
    connect();
    doLogin();
    initChatManager();
  }

  private void initChatManager() {
    chatManager = ChatManager.getInstanceFor(connection);
    chatManager.addChatListener(chatManagerListener);
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
   * {@inheritDoc}.
   */
  @Override
  public void receiveMessage(String message) {
    throw new UnsupportedOperationException("Empty method yet.");
  }


  /**
   * {@inheritDoc}.
   */
  @Override
  public XMPPTCPConnection getConnection() {
    return connection;
  }


  @Override
  public void reconnect() {
    if (connection != null) {
      connection.disconnect();
      connection = null;
    }
    init();
  }

}

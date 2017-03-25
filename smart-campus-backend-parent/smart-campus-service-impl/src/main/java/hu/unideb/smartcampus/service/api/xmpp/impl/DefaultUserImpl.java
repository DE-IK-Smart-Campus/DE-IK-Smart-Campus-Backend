package hu.unideb.smartcampus.service.api.xmpp.impl;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.service.api.xmpp.DefaultUser;
import hu.unideb.smartcampus.service.api.xmpp.FeatureInjectorService;
import hu.unideb.smartcampus.service.api.xmpp.XmppClientConfigurationService;
import hu.unideb.smartcampus.shared.exception.ConnectionException;
import hu.unideb.smartcampus.shared.exception.LoginException;
import hu.unideb.smartcampus.shared.exception.XmppException;
import hu.unideb.smartcampus.shared.iq.provider.SubjectRequestIqProvider;
import hu.unideb.smartcampus.shared.iq.request.SubjectsIqRequest;

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

  @Autowired
  private XmppClientConfigurationService connectionConfigurationService;

  @Autowired
  private FeatureInjectorService featureInjectorService;


  /**
   * Init after class.
   */
  @PostConstruct
  public void init() {
    LOGGER.info("Starting default smart campus user...");
    try {
      initConnection(user, password);
      registerFeatures();
      registerCustomIQs();
      LOGGER.info("Default user is ready to accept messages.");
    } catch (XmppException e) {
      LOGGER.error("Error while logging in default user.", e);
    }
  }

  private void registerCustomIQs() {
     ProviderManager.addIQProvider(SubjectsIqRequest.ELEMENT, SubjectsIqRequest.NAMESPACE,
     new SubjectRequestIqProvider());
//    ProviderManager.addIQProvider(TestIq.ELEMENT, TestIq.NAMESPACE, new TestIqProvider());
  }


  private void registerFeatures() {
    featureInjectorService.registerFeaturesForConnection(connection);
  }

  private void initConnection(String username, String password) throws XmppException {
    XMPPTCPConnectionConfiguration conf = connectionConfigurationService
        .getXmppConfigurationByUserNameAndPassword(username, password);
    connection = new XMPPTCPConnection(conf);
    connect();
    doLogin();
  }

  private void connect() throws ConnectionException {
    try {
      connection.connect();
    } catch (SmackException | IOException | XMPPException | InterruptedException e) {
      LOGGER.error("Connection could not been established.", e);
      throw new ConnectionException("Error on connection to Ejabberd server.", e);
    }
  }

  private void doLogin() throws LoginException {
    try {
      connection.login();
    } catch (XMPPException | SmackException | IOException | InterruptedException e) {
      LOGGER.error("User could not log in.", e);
      throw new LoginException("Error on logging in to Ejabberd server.", e);
    }
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

  @PreDestroy
  public void preDestroy() {
    disconnectAndResetConnection();
  }

  private void disconnectAndResetConnection() {
    if (connection != null) {
      connection.disconnect();
    }
    resetConnection();
  }


  private void resetConnection() {
    connection = null;
  }

}

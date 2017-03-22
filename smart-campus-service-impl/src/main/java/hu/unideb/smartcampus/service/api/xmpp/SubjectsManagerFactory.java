package hu.unideb.smartcampus.service.api.xmpp;

import java.util.Map;
import java.util.WeakHashMap;

import org.jivesoftware.smack.XMPPConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.xmpp.handler.SubjectRequestIqRequestHandler;

/**
 * Subjects manager factory.
 */
@Service
public final class SubjectsManagerFactory {

  /**
   * Bean factory.
   */
  @Autowired
  private AutowireCapableBeanFactory beanFactory;

  /**
   * Subject handler.
   */
  @Autowired
  private SubjectRequestIqRequestHandler handler;

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(SubjectsManagerFactory.class);

  /**
   * Map an XMPPConnection with it SubjectsManager. This map have a key-value pair for every active
   * connection.
   */
  private static Map<XMPPConnection, SubjectsManager> instances = new WeakHashMap<>();

  /**
   * Register connection.
   */
  public SubjectsManager registerConnectionSubjectManager(XMPPConnection connection) {
    LOGGER.info("Registering SubjectManager to connection {}", connection);
    SubjectsManager manager = instances.get(connection);
    if (manager == null) {
      manager = new SubjectsManager(connection, handler);
      beanFactory.autowireBean(manager);
      instances.put(connection, manager);
    }
    return manager;
  }

}

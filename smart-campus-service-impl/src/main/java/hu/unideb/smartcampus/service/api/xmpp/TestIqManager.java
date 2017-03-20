package hu.unideb.smartcampus.service.api.xmpp;

import java.util.Map;
import java.util.WeakHashMap;

import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.unideb.smartcampus.shared.iq.TestIq;

/**
 * Test Iq manager.
 */
public final class TestIqManager extends Manager {

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(TestIqManager.class);

  /**
   * Map an XMPPConnection with it AdHocCommandManager. This map have a key-value pair for every
   * active connection.
   */
  private static Map<XMPPConnection, TestIqManager> instances = new WeakHashMap<>();

  /**
   * Disc.
   */
  private ServiceDiscoveryManager serviceDiscoveryManager;

  /**
   * Get for.
   */
  public static TestIqManager getInstanceFor(XMPPConnection connection) {
    TestIqManager ahcm = instances.get(connection);
    if (ahcm == null) {
      ahcm = new TestIqManager(connection);
      instances.put(connection, ahcm);
    }
    return ahcm;
  }

  /**
   * Private constructor.
   */
  private TestIqManager(XMPPConnection connection) {
    super(connection);
    this.serviceDiscoveryManager = ServiceDiscoveryManager.getInstanceFor(connection);
    LOGGER.info("{}", serviceDiscoveryManager.toString());
    ServiceDiscoveryManager.getInstanceFor(connection).addFeature(TestIq.NAMESPACE);


    // The packet listener and the filter for processing some AdHoc Commands
    // Packets

    connection.registerIQRequestHandler(
        new AbstractIqRequestHandler(TestIq.ELEMENT, TestIq.NAMESPACE, IQ.Type.set, Mode.async) {
          @Override
          public IQ handleIQRequest(IQ iqRequest) {
            return iqRequest;
          }
        });

  }

}

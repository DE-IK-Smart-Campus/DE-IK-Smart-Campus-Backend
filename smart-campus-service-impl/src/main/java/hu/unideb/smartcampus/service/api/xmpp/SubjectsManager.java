package hu.unideb.smartcampus.service.api.xmpp;

import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.unideb.smartcampus.shared.iq.SubjectsIq;

/**
 * Test Iq manager.
 */
public final class SubjectsManager extends Manager {

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(SubjectsManager.class);

  /**
   * Connection.
   */
  private XMPPConnection connection;

  /**
   * Disc.
   */
  private ServiceDiscoveryManager serviceDiscoveryManager;

  /**
   * Private constructor.
   */
  SubjectsManager(XMPPConnection connection, AbstractIqRequestHandler handler) {
    super(connection);
    this.serviceDiscoveryManager = ServiceDiscoveryManager.getInstanceFor(connection);
    LOGGER.info("{}", serviceDiscoveryManager.toString());
    ServiceDiscoveryManager.getInstanceFor(connection).addFeature(SubjectsIq.NAMESPACE);
    connection.registerIQRequestHandler(handler);
    this.connection = connection;
  }

  /**
   * Send subject retrieval stanza.
   */
  public void sendSubjectRetrievalRequest(String from, String userId) throws NotConnectedException {
    SubjectsIq iq = new SubjectsIq();
    iq.setType(Type.get);
    iq.setFrom(from);
    iq.setStudent(userId);
    connection.sendStanza(iq);
  }

}

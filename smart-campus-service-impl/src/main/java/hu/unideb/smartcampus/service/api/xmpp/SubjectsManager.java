package hu.unideb.smartcampus.service.api.xmpp;

import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;

import hu.unideb.smartcampus.shared.iq.AbstractSmartCampusIq;

/**
 * Subject IQ manager.
 */
public final class SubjectsManager extends Manager {

  /**
   * Private constructor.
   */
  SubjectsManager(XMPPConnection connection, AbstractIqRequestHandler handler) {
    super(connection);
    ServiceDiscoveryManager.getInstanceFor(connection)
        .addFeature(AbstractSmartCampusIq.BASE_NAMESPACE);
    connection.registerIQRequestHandler(handler);
  }

}

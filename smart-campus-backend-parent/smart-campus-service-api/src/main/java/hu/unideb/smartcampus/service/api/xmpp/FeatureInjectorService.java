package hu.unideb.smartcampus.service.api.xmpp;

import org.jivesoftware.smack.XMPPConnection;

/**
 * Known custom Feature injector service.
 */
public interface FeatureInjectorService {

  /**
   * Register custom features for XMPP connection.
   */
  void registerFeaturesForConnectionWithHandlers(XMPPConnection connection);

}

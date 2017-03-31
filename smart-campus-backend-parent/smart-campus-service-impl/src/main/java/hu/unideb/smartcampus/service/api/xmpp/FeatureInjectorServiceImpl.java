package hu.unideb.smartcampus.service.api.xmpp;


import java.util.List;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of feature injector service.
 */
@Service
public class FeatureInjectorServiceImpl implements FeatureInjectorService {

  private static final Logger LOGGER = LoggerFactory.getLogger(FeatureInjectorServiceImpl.class);

  /**
   * Handlers.
   */
  @Autowired
  private List<AbstractIqRequestHandler> handlers;

  /**
   * {@inheritDoc}.
   */
  @Override
  public void registerFeaturesForConnectionWithHandlers(XMPPConnection connection) {
    handlers.stream().forEach(handler -> {
      LOGGER.info("Registering feature: {}", handler.getElement());
      ServiceDiscoveryManager.getInstanceFor(connection).addFeature(handler.getElement());
      connection.registerIQRequestHandler(handler);
    });
  }

}

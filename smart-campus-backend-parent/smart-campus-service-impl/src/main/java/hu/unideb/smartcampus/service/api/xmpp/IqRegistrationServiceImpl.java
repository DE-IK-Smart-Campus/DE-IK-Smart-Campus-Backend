package hu.unideb.smartcampus.service.api.xmpp;


import java.util.Map;

import org.jivesoftware.smack.provider.ProviderManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.shared.exception.IqRegistrationException;
import hu.unideb.smartcampus.shared.iq.context.IqClassContext;
import hu.unideb.smartcampus.shared.iq.provider.BaseSmartCampusIqIntrospectionProvider;
import hu.unideb.smartcampus.shared.iq.provider.BaseSmartCampusIqProvider;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;

/**
 * Implementation of feature injector service.
 */
@Service
public class IqRegistrationServiceImpl implements IqRegistrationService {

  private static final Logger LOGGER = LoggerFactory.getLogger(IqRegistrationServiceImpl.class);

  @Override
  public void registerIqWithProviders() throws IqRegistrationException {
    LOGGER.info("Registering IQ's with providers.");
    Map<Class<? extends BaseSmartCampusIqRequest>, Class<? extends BaseSmartCampusIqProvider>> iqWithProvider =
        getIqWithProvider();
    for (Class clazz : iqWithProvider.keySet()) {
      registerIqWithProvider(iqWithProvider, clazz);
    }
    Map<Class<? extends BaseSmartCampusIqRequest>, Class<? extends BaseSmartCampusIqIntrospectionProvider>> iqWithIntrospectionProvider =
        getIqWithIntrospectionProvider();
    for (Class clazz : iqWithIntrospectionProvider.keySet()) {
      registerIqWithIntrospectionProvider(iqWithIntrospectionProvider, clazz);
    }

  }

  private void registerIqWithProvider(
      Map<Class<? extends BaseSmartCampusIqRequest>, Class<? extends BaseSmartCampusIqProvider>> iqWithProvider,
      Class iq) throws IqRegistrationException {
    try {
      Class<? extends BaseSmartCampusIqProvider> provider = iqWithProvider.get(iq);
      LOGGER.info("{} with introspection provider {}", iq, provider);
      ProviderManager.addIQProvider(((BaseSmartCampusIqRequest) iq.newInstance()).getElement(),
          BaseSmartCampusIqRequest.BASE_NAMESPACE, provider.newInstance());
    } catch (InstantiationException | IllegalAccessException e) {
      throw new IqRegistrationException(e);
    }
  }

  private void registerIqWithIntrospectionProvider(
      Map<Class<? extends BaseSmartCampusIqRequest>, Class<? extends BaseSmartCampusIqIntrospectionProvider>> iqWithProvider,
      Class iq) throws IqRegistrationException {
    try {
      Class<? extends BaseSmartCampusIqIntrospectionProvider> provider = iqWithProvider.get(iq);
      LOGGER.info("{} with provider {}", iq, provider);
      ProviderManager.addIQProvider(((BaseSmartCampusIqRequest) iq.newInstance()).getElement(),
          BaseSmartCampusIqRequest.BASE_NAMESPACE, provider.newInstance());
    } catch (InstantiationException | IllegalAccessException e) {
      throw new IqRegistrationException(e);
    }
  }

  private Map<Class<? extends BaseSmartCampusIqRequest>, Class<? extends BaseSmartCampusIqProvider>> getIqWithProvider()
      throws IqRegistrationException {
    Map<Class<? extends BaseSmartCampusIqRequest>, Class<? extends BaseSmartCampusIqProvider>> iqWithProvider;
    try {
      iqWithProvider = IqClassContext.getIqWithProvider();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new IqRegistrationException(e);
    }
    return iqWithProvider;
  }

  private Map<Class<? extends BaseSmartCampusIqRequest>, Class<? extends BaseSmartCampusIqIntrospectionProvider>> getIqWithIntrospectionProvider()
      throws IqRegistrationException {
    Map<Class<? extends BaseSmartCampusIqRequest>, Class<? extends BaseSmartCampusIqIntrospectionProvider>> iqWithProvider;
    try {
      iqWithProvider = IqClassContext.getIqWithIntrospectionProvider();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new IqRegistrationException(e);
    }
    return iqWithProvider;
  }



}

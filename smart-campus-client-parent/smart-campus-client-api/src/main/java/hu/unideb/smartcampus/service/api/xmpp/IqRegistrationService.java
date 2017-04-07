package hu.unideb.smartcampus.service.api.xmpp;

import hu.unideb.smartcampus.shared.exception.IqRegistrationException;

/**
 * Known custom Feature injector service.
 */
public interface IqRegistrationService {

  /**
   * Register custom IQ's with providers.
   */
  void registerIqWithProviders() throws IqRegistrationException;
}

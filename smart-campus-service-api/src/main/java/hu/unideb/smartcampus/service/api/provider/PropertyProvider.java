package hu.unideb.smartcampus.service.api.provider;

import java.util.Optional;
import hu.unideb.smartcampus.shared.enumeration.ConfigPropertyKey;

/**
 * Configuration property provider.
 */
public interface PropertyProvider {

  /**
   * Retrieves configuration property value by configuration property key.
   */
  Optional<String> getPropertyValue(ConfigPropertyKey configPropertyKey);
}

package hu.unideb.smartcampus.service.api.authentication;

import hu.unideb.smartcampus.service.api.domain.ChatProperties;

/**
 * Chat properties holder service.
 */
public interface ChatPropertiesHolderService {

  /**
   * Get chat properties.
   */
  ChatProperties getChatProperties();

}

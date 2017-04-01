package hu.unideb.smartcampus.service.api;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import hu.unideb.smartcampus.domain.ChatProperties;
import hu.unideb.smartcampus.service.api.authentication.ChatPropertiesHolderService;

/**
 * Service for chat properties.
 */
@Service
public class ChatPropertiesHolderServiceImpl implements ChatPropertiesHolderService {

  /**
   * BOSH service URL.
   */
  @Resource(lookup = "java:global/converse.bosh.service.url")
  private String boshServiceUrl;

  /**
   * Credentials URL.
   */
  @Resource(lookup = "java:global/converse.credentials.url")
  private String credentialsUrl;

  /**
   * MUC domain.
   */
  @Resource(lookup = "java:global/converse.mucdomain")
  private String mucDomain;

  /**
   * {@inheritDoc}.
   */
  @Override
  public ChatProperties getChatProperties() {
    return ChatProperties.builder()
        .boshServiceUrl(boshServiceUrl)
        .credentialsUrl(credentialsUrl)
        .mucDomain(mucDomain)
        .build();
  }
}

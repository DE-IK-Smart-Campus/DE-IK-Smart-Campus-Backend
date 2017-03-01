package hu.unideb.smartcampus.webservice.api.provider.impl;

import com.google.common.collect.ImmutableMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import javax.annotation.PostConstruct;
import hu.unideb.smartcampus.shared.enumeration.ConfigPropertyKey;
import hu.unideb.smartcampus.webservice.api.provider.PropertyProvider;

@Component
@Scope(scopeName = "singleton")
public class PropertyProviderImpl implements PropertyProvider {

  private static final Logger LOGGER = LoggerFactory.getLogger(PropertyProviderImpl.class);

  private final PropertyConfig propertyConfig;
  private Map<ConfigPropertyKey, String> configProperties;

  @Autowired
  public PropertyProviderImpl(final PropertyConfig propertyConfig) {
    this.propertyConfig = propertyConfig;
  }

  @Override
  public String getPropertyValue(final ConfigPropertyKey configPropertyKey) {
    return this.configProperties.get(configPropertyKey);
  }

  /**
   * Init configuration property map.
   */
  @PostConstruct
  protected void initConfigProperties() {
    this.configProperties = ImmutableMap.<ConfigPropertyKey, String>builder()
        .put(ConfigPropertyKey.SMART_CAMPUS_XMPP_DOMAIN, this.propertyConfig.smartCampusXmppDomain)
        .put(ConfigPropertyKey.SMART_CAMPUS_XMPP_HOST, this.propertyConfig.smartCampusXmppHost)
        .put(ConfigPropertyKey.SMART_CAMPUS_EJABBERD_API_ENDPOINT, this.propertyConfig.smartCampusEjabberdApiEndpoint)
        .put(ConfigPropertyKey.SMART_CAMPUS_EJABBERD_API_HOST, this.propertyConfig.smartCampusEjabberdApiHost)
        .put(ConfigPropertyKey.SMART_CAMPUS_EJABBERD_API_PORT, this.propertyConfig.smartCampusEjabberdApiPort)
        .build();

    LOGGER.info("Smart-campus properties = \n{}", this.configProperties);
  }
}

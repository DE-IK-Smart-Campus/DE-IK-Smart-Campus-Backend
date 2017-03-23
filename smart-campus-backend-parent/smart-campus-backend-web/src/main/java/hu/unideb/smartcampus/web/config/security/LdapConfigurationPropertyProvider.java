package hu.unideb.smartcampus.web.config.security;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;

/**
 * Property provider for ldap based security configuration.
 *
 */
@Component
public class LdapConfigurationPropertyProvider {
  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(LdapConfigurationPropertyProvider.class);

  /**
   * Property container.
   */
  private Map<LdapProperties, String> props;

  /**
   * The property injector.
   */
  private LdapConfigurationProperties ldapProperties;

  /**
   * Constructor with property source parameter.
   * 
   * @param ldapProperties the property source.
   */
  @Autowired
  public LdapConfigurationPropertyProvider(final LdapConfigurationProperties ldapProperties) {
    this.ldapProperties = ldapProperties;
  }

  /**
   * Initialize the property container.
   */
  @PostConstruct
  protected void initConfigProperties() {
    this.props = ImmutableMap.<LdapProperties, String>builder()
        .put(LdapProperties.LDAP_PROVIDER_HOST, this.ldapProperties.ldapProviderHost)
        .put(LdapProperties.LDAP_PROVIDER_PORT, this.ldapProperties.ldapProviderPort)
        .put(LdapProperties.LDAP_GROUP_SERACH_BASE, this.ldapProperties.ldapGroupSearchBase)
        .put(LdapProperties.LDAP_PROVIDER_BASE_DN, this.ldapProperties.ldapProviderBaseDn)
        .put(LdapProperties.LDAP_PASSWORD_ATTRIBUTE_NAME, this.ldapProperties.passwordAttribute)
        .put(LdapProperties.LDAP_USERPATTERN, this.ldapProperties.ldapUserDnPattern)
        .put(LdapProperties.LDAP_GROUP_FILTER,this.ldapProperties.groupFilter)
        .build();

    LOGGER.debug("LDAP properties = \n{}", this.props);
  }

  /**
   * Provides the property value of the parameter.
   * 
   * @param property the property required
   * @return the value of the property
   */
  public String getProperty(LdapProperties property) {
    return props.get(property);
  }

}

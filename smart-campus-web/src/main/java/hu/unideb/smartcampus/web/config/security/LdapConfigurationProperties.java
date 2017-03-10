package hu.unideb.smartcampus.web.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Property holder for LDAP authentication.
 */
@Configuration
@Scope(scopeName = "singleton")
public class LdapConfigurationProperties {

  /**
   * The host of the LDAP provider.
   */
  @Value("${ldap.provider.host}")
  protected String ldapProviderHost;

  /**
   * The port of the LDAP server.
   */
  @Value("${ldap.provider.port}")
  protected String ldapProviderPort;

  /**
   * The base DN of the LDAP directory node.
   */
  @Value("${ldap.provider.basedn}")
  protected String ldapProviderBaseDn;

  /**
   * The pattern of the user search.
   */
  @Value("${ldap.userdnpattern}")
  protected String ldapUserDnPattern;

  /**
   * The base DN of the groups.
   */
  @Value("${ldap.groupsearchbase}")
  protected String ldapGroupSearchBase;

  /**
   * The password attribute of the user.
   */
  @Value("${ldap.passwordattribute}")
  protected String passwordAttribute;
  
  /**
   * Group search filter property.
   */
  @Value("${ldap.groupfilter}")
  protected String groupFilter;
  
}

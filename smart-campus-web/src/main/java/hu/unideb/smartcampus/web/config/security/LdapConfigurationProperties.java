package hu.unideb.smartcampus.web.config.security;

import javax.annotation.Resource;

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
  @Resource(lookup = "java:global/ldap.provider.host")
  protected String ldapProviderHost;

  /**
   * The port of the LDAP server.
   */
  @Resource(lookup = "java:global/ldap.provider.port")
  protected String ldapProviderPort;

  /**
   * The base DN of the LDAP directory node.
   */
  @Resource(lookup = "java:global/ldap.provider.basedn")
  protected String ldapProviderBaseDn;

  /**
   * The pattern of the user search.
   */
  @Resource(lookup = "java:global/ldap.userdnpattern")
  protected String ldapUserDnPattern;

  /**
   * The base DN of the groups.
   */
  @Resource(lookup = "java:global/ldap.groupsearchbase")
  protected String ldapGroupSearchBase;

  /**
   * The password attribute of the user.
   */
  @Resource(lookup = "java:global/ldap.passwordattribute")
  protected String passwordAttribute;

  /**
   * Group search filter property.
   */
  @Resource(lookup = "java:global/ldap.groupfilter")
  protected String groupFilter;

}

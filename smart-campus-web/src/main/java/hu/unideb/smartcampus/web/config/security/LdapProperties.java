package hu.unideb.smartcampus.web.config.security;


/**
 * The properties to provide basic LDAP authentication.
 *
 */
public enum LdapProperties {

  LDAP_PROVIDER_HOST,
  LDAP_PROVIDER_PORT,
  LDAP_PROVIDER_BASE_DN,
  LDAP_USERPATTERN,
  LDAP_GROUP_SERACH_BASE,
  LDAP_PASSWORD_ATTRIBUTE_NAME,
  LDAP_GROUP_FILTER;

}

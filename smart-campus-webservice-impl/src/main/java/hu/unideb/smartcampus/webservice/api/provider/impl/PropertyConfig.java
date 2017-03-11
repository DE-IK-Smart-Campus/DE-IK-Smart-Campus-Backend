package hu.unideb.smartcampus.webservice.api.provider.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Property configuration class for smart-campus properties.
 */
@Configuration
@Scope(scopeName = "singleton")
public class PropertyConfig {

  /**
   * Smart-campus xmpp domain.
   */
  @Resource(lookup = "java:global/smartcampus.xmpp.domain")
  protected String smartCampusXmppDomain;

  /**
   * Smart-campus xmpp host.
   */
  @Resource(lookup = "java:global/smartcampus.xmpp.host")
  protected String smartCampusXmppHost;

  /**
   * Smart-campus ejabberd api endpoint.
   */
  @Resource(lookup = "java:global/smartcampus.ejabberd.api.endpoint")
  protected String smartCampusEjabberdApiEndpoint;

  /**
   * Smart-campus ejabberd api port.
   */
  @Resource(lookup = "java:global/smartcampus.ejabberd.api.port")
  protected String smartCampusEjabberdApiPort;

  /**
   * Smart-campus ejabberd api host.
   */
  @Resource(lookup = "java:global/smartcampus.ejabberd.api.host")
  protected String smartCampusEjabberdApiHost;
}

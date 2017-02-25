package hu.unideb.smartcampus.service.api.provider.impl;

import org.springframework.beans.factory.annotation.Value;
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
  @Value("${smartcampus.xmpp.domain}")
  protected String smartCampusXmppDomain;

  /**
   * Smart-campus xmpp host.
   */
  @Value("${smartcampus.xmpp.host}")
  protected String smartCampusXmppHost;

  /**
   * Smart-campus ejabberd api endpoint.
   */
  @Value(("${smartcampus.ejabberd.api.endpoint}"))
  protected String smartCampusEjabberdApiEndpoint;

  /**
   * Smart-campus ejabberd api port.
   */
  @Value("${smartcampus.ejabberd.api.port}")
  protected String smartCampusEjabberdApiPort;

  /**
   * Smart-campus ejabberd api host.
   */
  @Value("${smartcampus.ejabberd.api.host}")
  protected String smartCampusEjabberdApiHost;
}

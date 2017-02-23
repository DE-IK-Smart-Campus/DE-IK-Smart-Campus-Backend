package hu.unideb.smartcampus.service.ejabberd;

import org.springframework.beans.factory.annotation.Value;

/**
 * Class for every service which indicates the ckass will control the Ejabberd XMPP server via REST
 * API.
 *
 */
public class EjabberdService {

  /**
   * Ejabberd REST API host.
   */
  @Value("${smartcampus.ejabberd.api.host}")
  protected static String HOST;

  /**
   * XMPP Server domain.
   */
  @Value("${smartcampus.xmpp.domain}")
  protected static String DOMAIN;

}

package hu.unideb.smartcampus.service.ejabberd;

/**
 * Class for every service which indicates the ckass will control the Ejabberd XMPP server via REST
 * API.
 *
 */
public class EjabberdService {

  /**
   * Ejabberd REST API host.
   */
  protected static final String HOST = "http://smartcampus:5280/api";

  /**
   * XMPP Server domain.
   */
  protected static final String DOMAIN = "smartcampus";

}

package hu.unideb.smartcampus.service.api.xmpp.impl;

import javax.annotation.Resource;

import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.bosh.BOSHConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.xmpp.XmppClientConfigurationService;

/**
 * XMPP Client configuration service.
 *
 */
@Service
public class XmppClientConfigurationServiceImpl implements XmppClientConfigurationService {

  private static final String HTTP_BIND = "/http-bind/";

  private static final Logger LOGGER =
      LoggerFactory.getLogger(XmppClientConfigurationServiceImpl.class);

  /**
   * XMPP host.
   */
  @Resource(lookup = "java:global/smartcampus.xmpp.host")
  private String host;

  /**
   * XMPP service.
   */
  @Resource(lookup = "java:global/smartcampus.xmpp.service")
  private String service;

  /**
   * XMPP port.
   */
  @Resource(lookup = "java:global/smartcampus.xmpp.port")
  private Integer port;

  @Override
  public BOSHConfiguration getBoshConfigurationByUserNameAndPassword(String username,
      String password) {
    LOGGER.info("Creating BOSH configuration to host:{} on port:{}", host, port);
    return BOSHConfiguration.builder().setHost(host).setServiceName(service).setFile(HTTP_BIND)
        .setPort(port).setSecurityMode(SecurityMode.disabled).setDebuggerEnabled(true)
        .setUsernameAndPassword(username, password).build();
  }

  @Override
  public XMPPTCPConnectionConfiguration getXmppConfigurationByUserNameAndPassword(String username,
      String password) {
    LOGGER.info("Creating XMPP configuration to host:{} on port:{}", host, port);
    return XMPPTCPConnectionConfiguration.builder().setHost(host).setServiceName(service)
        .setPort(port).setSecurityMode(SecurityMode.disabled).setDebuggerEnabled(true)
        .setUsernameAndPassword(username, password).build();
  }

}

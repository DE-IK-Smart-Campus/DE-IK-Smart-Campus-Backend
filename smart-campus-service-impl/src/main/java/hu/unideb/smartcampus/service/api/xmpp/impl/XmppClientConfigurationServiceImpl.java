package hu.unideb.smartcampus.service.api.xmpp.impl;

import javax.annotation.Resource;

import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.bosh.BOSHConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.stringprep.XmppStringprepException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.xmpp.XmppClientConfigurationService;
import hu.unideb.smartcampus.shared.exception.XmppException;

/**
 * XMPP Client configuration service where we can configure clients to default TCP and BOSH.
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
   * XMPP TCP port.
   */
  @Resource(lookup = "java:global/smartcampus.xmpp.tcp.port")
  private Integer tcpPort;

  /**
   * XMPP BOSH port.
   */
  @Resource(lookup = "java:global/smartcampus.xmpp.bosh.port")
  private Integer boshPort;

  @Override
  public BOSHConfiguration getBoshConfigurationByUserNameAndPassword(String username,
      String password) throws XmppException {
    LOGGER.info("Creating BOSH configuration to host:{} on port:{}", host, boshPort);
    try {
      return BOSHConfiguration.builder()
          .setHost(host)
          .setXmppDomain(service)
          .setFile(HTTP_BIND)
          .setPort(boshPort)
          .setSecurityMode(SecurityMode.disabled)
          .setUsernameAndPassword(username, password)
          .build();
    } catch (XmppStringprepException e) {
      LOGGER.error("Error on creating BOSH configuration.",e);
      throw new XmppException("Error on creating BOSH configuration.",e);
    }
  }

  @Override
  public XMPPTCPConnectionConfiguration getXmppConfigurationByUserNameAndPassword(String username,
      String password) throws XmppException {
    LOGGER.info("Creating XMPP configuration to host:{} on port:{}", host, tcpPort);
    try {
      return XMPPTCPConnectionConfiguration.builder()
          .setHost(host)
          .setXmppDomain(service)
          .setPort(tcpPort)
          .setSecurityMode(SecurityMode.disabled)
          .setDebuggerEnabled(true)
          .setUsernameAndPassword(username, password)
          .build();
    } catch (XmppStringprepException e) {
      LOGGER.error("Error on creating TCP configuration.",e);
      throw new XmppException("Error on creating TCP configuration.",e);
    }
  }

}

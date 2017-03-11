package hu.unideb.smartcampus.service.api.xmpp.impl;

import javax.annotation.Resource;

import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.webservice.api.xmpp.XmppClientConfigurationService;

@Service
public class XmppClientConfigurationServiceImpl implements XmppClientConfigurationService {

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
  public XMPPTCPConnectionConfiguration getConfigurationByUsernameAndPassword(String username,
      String password) {
    return XMPPTCPConnectionConfiguration.builder().setHost(host).setServiceName(service)
        .setPort(port).setSecurityMode(SecurityMode.disabled).setDebuggerEnabled(true)
        .setUsernameAndPassword(username, password).build();
  }

}

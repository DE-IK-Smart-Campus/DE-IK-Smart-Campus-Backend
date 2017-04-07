package hu.unideb.smartcampus.service.api.rss.facebook.credentials;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class FacebookPropertiesHolder {

  @Resource(lookup = "java:global/facebook.clientid")
  String clientId;

  @Resource(lookup = "java:global/facebook.clientsecretid")
  String clientSecretId;

}

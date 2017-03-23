package hu.unideb.smartcampus.service.api.security;


import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@Scope(scopeName = "singleton")
public class SecurityProperties {

  @Resource(lookup = "java:global/backend.host")
  protected String host;

  @Resource(lookup = "java:global/backend.port")
  protected String port;

  @Resource(lookup = "java:global/backend.context")
  protected String context;

}

package hu.unideb.smartcampus.service.api.security;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;

import hu.unideb.smartcampus.service.api.authentication.SecurityProperty;

@Component
@Scope(scopeName = "singleton")
public class SecurityPropertyProvider {

  private Map<SecurityProperty, String> data;

  @Resource(lookup = "java:global/backend.host")
  protected String host;

  @Resource(lookup = "java:global/backend.port")
  protected String port;

  @Resource(lookup = "java:global/backend.context")
  protected String context;


  @PostConstruct
  private void initProperties() {
    this.data = ImmutableMap.<SecurityProperty, String>builder()
        .put(SecurityProperty.HOST, host)
        .put(SecurityProperty.PORT, port)
        .put(SecurityProperty.CONTEXT, context).build();

  }

  public String getProperty(SecurityProperty property) {
    return data.get(property);
  }

}

package hu.unideb.smartcampus.service.api.security;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;

import hu.unideb.smartcampus.service.api.authentication.SecurityProperty;

@Component
public class SecurityPropertyProvider {

  private Map<SecurityProperty, String> data;

  @Autowired
  private SecurityProperties properties;

  @PostConstruct
  private void initProperties() {
    this.data = ImmutableMap.<SecurityProperty, String>builder()
        .put(SecurityProperty.HOST, this.properties.host)
        .put(SecurityProperty.PORT, this.properties.port)
        .put(SecurityProperty.CONTEXT, this.properties.context).build();

  }

  public String getProperty(SecurityProperty property) {
    return data.get(property);
  }

}

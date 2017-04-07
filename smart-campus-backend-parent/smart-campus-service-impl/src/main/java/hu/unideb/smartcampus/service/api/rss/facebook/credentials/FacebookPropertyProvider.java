package hu.unideb.smartcampus.service.api.rss.facebook.credentials;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;

@Component
public class FacebookPropertyProvider {
  
  private Map<FacebookProperty, String> props;

  private FacebookPropertiesHolder properties;

  @Autowired
  public FacebookPropertyProvider(final FacebookPropertiesHolder properties) {
    this.properties = properties;
  }

  @PostConstruct
  protected void initConfigProperties() {
    this.props = ImmutableMap.<FacebookProperty, String>builder()
        .put(FacebookProperty.CLIENT_ID,properties.clientId)
        .put(FacebookProperty.CLIENT_SECRET_ID,properties.clientSecretId)
        .build();

  }

  public String getProperty(FacebookProperty property) {
    return props.get(property);
  }  
}

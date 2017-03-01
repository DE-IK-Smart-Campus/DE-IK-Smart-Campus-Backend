package hu.unideb.smartcampus.webservice.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


/**
 * Configuration class for web-service module.
 */
@Configuration
@ComponentScan("hu.unideb.smartcampus.webservice.api")
@PropertySource("classpath:smartcampus.properties")
public class WebServiceConfiguration {

  /**
   * Construct an instance of PropertySourcesPlaceholderConfigurer.
   *
   * @return a bean instance of PropertySourcesPlaceholderConfigurer.
   */
  @Bean
  public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

}

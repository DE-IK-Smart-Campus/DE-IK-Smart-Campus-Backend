package hu.unideb.smartcampus.service.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import hu.unideb.smartcampus.persistence.config.PersistenceConfiguration;

/**
 * Configuration class for service module.
 */
@Configuration
@Import(PersistenceConfiguration.class)
@ComponentScan("hu.unideb.smartcampus.service")
@PropertySource("classpath:smartcampus.properties")
public class ServiceConfiguration {

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

}

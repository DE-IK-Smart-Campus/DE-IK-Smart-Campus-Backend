package hu.unideb.smartcampus.service.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.databind.ObjectMapper;

import hu.unideb.smartcampus.persistence.config.PersistenceConfiguration;

/**
 * Configuration class for service module.
 */
@Configuration
@Import(PersistenceConfiguration.class)
@ComponentScan("hu.unideb.smartcampus.service.api")
public class ServiceConfiguration {

  /**
   * Object mapper for JSON mapper.
   */
  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}

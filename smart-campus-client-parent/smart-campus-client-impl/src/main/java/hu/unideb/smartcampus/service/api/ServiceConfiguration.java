package hu.unideb.smartcampus.service.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Configuration class for service module.
 */
@Configuration
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

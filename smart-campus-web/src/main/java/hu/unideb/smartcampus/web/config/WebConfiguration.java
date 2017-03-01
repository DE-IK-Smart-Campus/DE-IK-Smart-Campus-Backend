package hu.unideb.smartcampus.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import hu.unideb.smartcampus.service.api.config.ServiceConfiguration;
import hu.unideb.smartcampus.webservice.api.config.WebServiceConfiguration;

/**
 * Configuration class for web module.
 */
@Configuration
@Import({ServiceConfiguration.class, WebServiceConfiguration.class})
@ComponentScan("hu.unideb.smartcampus.web.controller")
public class WebConfiguration {
}

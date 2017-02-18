package hu.unideb.smartcampus.web.config;

import hu.unideb.smartcampus.service.api.config.ServiceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan("hu.unideb.smartcampus.web.controller")
public class WebConfiguration {
}

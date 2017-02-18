package hu.unideb.smartcampus.service.api.config;

import hu.unideb.smartcampus.persistence.config.PersistenceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceConfiguration.class)
@ComponentScan("hu.unideb.smartcampus.service.api")
public class ServiceConfiguration {
}

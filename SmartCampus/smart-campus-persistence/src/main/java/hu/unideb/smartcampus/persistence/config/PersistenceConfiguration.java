package hu.unideb.smartcampus.persistence.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("hu.unideb.smartcampus.persistence.repository")
@EntityScan("hu.unideb.smartcampus.persistence.entity")
public class PersistenceConfiguration {
}

package hu.unideb.smartcampus.persistence.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuration class for persistence module.
 */
@Configuration
@EnableJpaRepositories(basePackages = "hu.unideb.smartcampus.persistence.repository")
@EntityScan(basePackages = "hu.unideb.smartcampus.persistence.entity")
public class PersistenceConfiguration {

}

package hu.unideb.smartcampus.persistence.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration test class for persistence module.
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EntityScan(basePackages = "hu.unideb.smartcampus.persistence.entity")
@EnableJpaRepositories(basePackages = "hu.unideb.smartcampus.persistence.repository")
public class TestPersistenceConfiguration {

}

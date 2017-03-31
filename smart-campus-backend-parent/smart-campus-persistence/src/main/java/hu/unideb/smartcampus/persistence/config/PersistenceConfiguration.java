package hu.unideb.smartcampus.persistence.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration class for persistence module.
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableMongoRepositories("hu.unideb.smartcampus.persistence.mongo")
@EntityScan(basePackages = "hu.unideb.smartcampus.persistence.entity")
@EnableJpaRepositories(basePackages = "hu.unideb.smartcampus.persistence.repository")
public class PersistenceConfiguration {

}

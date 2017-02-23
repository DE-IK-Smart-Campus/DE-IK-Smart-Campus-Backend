package hu.unideb.smartcampus.persistence.repository;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import hu.unideb.smartcampus.persistence.config.PersistenceConfiguration;
import hu.unideb.smartcampus.shared.annotation.IntegrationTest;

/**
 * Base Repository super class for integrating tests in the persistence layer.
 */
@IntegrationTest
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PersistenceConfiguration.class, webEnvironment = WebEnvironment.NONE)
public class BaseRepositoryIntegrationTestHelper {
}

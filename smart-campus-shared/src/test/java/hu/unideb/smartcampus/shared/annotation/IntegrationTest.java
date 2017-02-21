package hu.unideb.smartcampus.shared.annotation;

import static hu.unideb.smartcampus.shared.profile.name.ProfileName.INTEGRATION_PROFILE;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for integration test.
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Transactional
@ActiveProfiles(profiles = INTEGRATION_PROFILE)
public @interface IntegrationTest {

}

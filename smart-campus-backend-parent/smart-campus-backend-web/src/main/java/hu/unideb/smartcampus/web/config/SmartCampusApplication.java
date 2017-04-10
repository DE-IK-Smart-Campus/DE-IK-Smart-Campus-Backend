package hu.unideb.smartcampus.web.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

/**
 * SmartCampusApplication class.
 */
@SpringBootApplication
@Import(WebConfiguration.class)
public class SmartCampusApplication extends SpringBootServletInitializer {

  /**
   * Main method.
   */
  public static void main(final String[] args) {
    SpringApplication.run(SmartCampusApplication.class, args);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
    return application.sources(SmartCampusApplication.class);
  }


}

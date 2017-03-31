package hu.unideb.smartcampus.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import hu.unideb.smartcampus.web.config.security.SmartCampusAuthenticationProvider;
import hu.unideb.smartcampus.web.config.security.SmartCampusAuthenticationSuccessHandler;
import hu.unideb.smartcampus.web.config.security.SmartCampusLogoutSuccessHandler;

/**
 * Security configuration collector.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@SuppressWarnings("PMD")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  protected static final String COLON = ":";
  protected static final String LDAP_PROTOCOL_PREFIX = "ldap://";

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http.authenticationProvider(authenticationProvider()).csrf().disable().formLogin().successForwardUrl("/dashboard").loginProcessingUrl("/login")
        .failureForwardUrl("/error").successHandler(authenticationSuccessHandler()).usernameParameter("username")
        .passwordParameter("password").and().logout().logoutUrl("/logout").logoutSuccessUrl("/").and().exceptionHandling();
    // @formatter:on
  }

  /**
   * TODO.
   */
  @Bean
  public AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new SmartCampusAuthenticationSuccessHandler();
  }

  /**
   * TODO.
   */
  @Bean
  public LogoutSuccessHandler logoutSuccessHandler() {
    return new SmartCampusLogoutSuccessHandler();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    return new SmartCampusAuthenticationProvider();
  }

}

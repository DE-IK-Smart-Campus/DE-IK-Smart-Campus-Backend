package hu.unideb.smartcampus.web.config;

import java.util.Arrays;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.RequestMatcher;

import hu.unideb.smartcampus.web.config.security.LdapConfigurationPropertyProvider;
import hu.unideb.smartcampus.web.config.security.LdapProperties;
import hu.unideb.smartcampus.web.config.security.SmartCampusApiRequestMatcher;
import hu.unideb.smartcampus.web.config.security.SmartCampusAuthenticationSuccessHandler;
import hu.unideb.smartcampus.web.config.security.SmartCampusLogoutSuccessHandler;
import hu.unideb.smartcampus.web.config.security.SmartCampusSynchronizingContextMapper;

/**
 * Security configuration collector.
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@SuppressWarnings("PMD")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  protected static final String COLON = ":";
  protected static final String LDAP_PROTOCOL_PREFIX = "ldap://";

  @Autowired
  protected LdapConfigurationPropertyProvider ldapConfigurationPropertyProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http.csrf().disable().formLogin().successForwardUrl("/dashboard").loginProcessingUrl("/login")
        .usernameParameter("username").passwordParameter("password").successHandler(authenticationSuccessHandler()).and().exceptionHandling()
        .authenticationEntryPoint(authenticationEntryPoint()).and().logout().logoutSuccessHandler(logoutSuccessHandler()).logoutUrl("/logout");

    http.httpBasic();
    // @formatter:on
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.ldapAuthentication()
        .userDnPatterns(
            ldapConfigurationPropertyProvider.getProperty(LdapProperties.LDAP_USERPATTERN))
        .groupSearchBase(
            ldapConfigurationPropertyProvider.getProperty(LdapProperties.LDAP_GROUP_SERACH_BASE))
        .groupSearchFilter(
            ldapConfigurationPropertyProvider.getProperty(LdapProperties.LDAP_GROUP_FILTER))
        .contextSource(contextSource()).passwordCompare()
        .passwordEncoder(new LdapShaPasswordEncoder())
        .passwordAttribute(ldapConfigurationPropertyProvider
            .getProperty(LdapProperties.LDAP_PASSWORD_ATTRIBUTE_NAME))
        .and().userDetailsContextMapper(userDetailsContextMapper());
  }

  /**
   * UserDetailsContextMapper of the sercurity.
   *
   * @return the UserDetailsContextMapper of the security configuration
   */
  @Bean
  public UserDetailsContextMapper userDetailsContextMapper() {
    return new SmartCampusSynchronizingContextMapper();
  }

  /**
   * Context source bean.
   */
  @Bean
  public DefaultSpringSecurityContextSource contextSource() {
    return new DefaultSpringSecurityContextSource(
        Arrays.asList(LDAP_PROTOCOL_PREFIX
            + ldapConfigurationPropertyProvider.getProperty(LdapProperties.LDAP_PROVIDER_HOST)
            + COLON
            + ldapConfigurationPropertyProvider.getProperty(LdapProperties.LDAP_PROVIDER_PORT)),
        ldapConfigurationPropertyProvider.getProperty(LdapProperties.LDAP_PROVIDER_BASE_DN));
  }



  /**
   * TODO.
   */
  @Bean
  public AuthenticationEntryPoint authenticationEntryPoint() {
    DelegatingAuthenticationEntryPoint delegatingAuthenticationEntryPoint =
        new DelegatingAuthenticationEntryPoint(delegatedEntryPoints());
    delegatingAuthenticationEntryPoint.setDefaultEntryPoint(formEntryPoint());
    return delegatingAuthenticationEntryPoint;
  }

  /**
   * TODO.
   * 
   */
  @Bean
  public LinkedHashMap<RequestMatcher, AuthenticationEntryPoint> delegatedEntryPoints() {
    LinkedHashMap<RequestMatcher, AuthenticationEntryPoint> entrypoints = new LinkedHashMap<>();
    entrypoints.put(apiRequestMatcher(), apiEntryPoint());
    return entrypoints;
  }

  /**
   * TODO.
   * 
   */
  @Bean
  public RequestMatcher apiRequestMatcher() {
    return new SmartCampusApiRequestMatcher("/integration");
  }

  /**
   * TODO.
   * 
   */
  @Bean
  public AuthenticationEntryPoint apiEntryPoint() {
    BasicAuthenticationEntryPoint basicAuthenticationEntryPoint =
        new BasicAuthenticationEntryPoint();
    basicAuthenticationEntryPoint.setRealmName("API");
    return basicAuthenticationEntryPoint;
  }

  /**
   * TODO.
   * 
   */
  @Bean
  public AuthenticationEntryPoint formEntryPoint() {
    return new LoginUrlAuthenticationEntryPoint("/login");
  }
  
  /**
   * TODO.
   * 
   */
  @Bean
  public AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new SmartCampusAuthenticationSuccessHandler();
  }

  /**
   * TODO.
   * 
   */
  @Bean
  public LogoutSuccessHandler logoutSuccessHandler() {
    return new SmartCampusLogoutSuccessHandler();
  }

}

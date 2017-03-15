package hu.unideb.smartcampus.web.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import hu.unideb.smartcampus.web.config.security.LdapConfigurationPropertyProvider;
import hu.unideb.smartcampus.web.config.security.LdapProperties;
import hu.unideb.smartcampus.web.config.security.SmartCampusLogoutSuccessHandler;
import hu.unideb.smartcampus.web.config.security.SmartCampusSynchronizingContextMapper;

@Configuration
@EnableWebSecurity
@SuppressWarnings({"PMD.SignatureDeclareThrowsException", "checkstyle:indentation"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static final String COLON = ":";

  private static final String LDAP_PROTOCOL_PREFIX = "ldap://";

  @Autowired
  private LdapConfigurationPropertyProvider ldapConfigurationPropertyProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http.csrf().disable().authorizeRequests() // TODO Remove
        .antMatchers("/", "/index").permitAll().anyRequest().authenticated().and().httpBasic().and()
        .rememberMe().and().logout().logoutSuccessHandler(logoutSuccessHandler());
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
   * UserDetailsContextMapper of the sercurity.
   *
   * @return the UserDetailsContextMapper of the security configuration
   */
  @Bean
  public UserDetailsContextMapper userDetailsContextMapper() {
    return new SmartCampusSynchronizingContextMapper();
  }

  /**
   * LogoutSuccessHandler to log out from Ejabberd.
   *
   * @return the LogoutSuccessHandler
   */
  @Bean
  public LogoutSuccessHandler logoutSuccessHandler() {
    return new SmartCampusLogoutSuccessHandler();
  }
}

package hu.unideb.smartcampus.service.api.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
public class QuartzConfiguration {

  @Autowired
  private DataSource dataSource;

  @Autowired
  private ResourceLoader resourceLoader;

  @Bean
  public SchedulerFactoryBean schedulerFactoryBean() {
    SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
    schedulerFactoryBean.setJobFactory(springBeanJobFactory());
    Resource resource = resourceLoader.getResource("classpath:quartz.properties");
    schedulerFactoryBean.setConfigLocation(resource);
    schedulerFactoryBean.setDataSource(dataSource);
    return schedulerFactoryBean;
  }

  @Bean
  public SpringBeanJobFactory springBeanJobFactory() {
    return new SpringBeanJobFactory();
  }

}

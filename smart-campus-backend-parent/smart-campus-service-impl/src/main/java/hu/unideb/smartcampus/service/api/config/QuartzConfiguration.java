package hu.unideb.smartcampus.service.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import hu.unideb.smartcampus.service.api.xmpp.XmppLoginJob;

@Configuration
public class QuartzConfiguration {

  @Bean
  public JobDetailFactoryBean jobDetailFactory() {
    JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
    jobDetailFactoryBean.setJobClass(XmppLoginJob.class);
    jobDetailFactoryBean.setDurability(true);
    return jobDetailFactoryBean;
  }

  @Bean
  public CronTriggerFactoryBean cronTriggerFactoryBean() {
    CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
    cronTriggerFactoryBean.setJobDetail(jobDetailFactory().getObject());
    cronTriggerFactoryBean.setCronExpression("0 0/1 * * * ?");
    return cronTriggerFactoryBean;
  }

  @Bean
  public SchedulerFactoryBean schedulerFactoryBean() {
    SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
    schedulerFactoryBean.setJobFactory(springBeanJobFactory());
    schedulerFactoryBean.setTriggers(cronTriggerFactoryBean().getObject());
    schedulerFactoryBean.setJobDetails(jobDetailFactory().getObject());
    
    return schedulerFactoryBean;
  }

  @Bean
  public SpringBeanJobFactory springBeanJobFactory(){
    return new SpringBeanJobFactory();
  }
  
}

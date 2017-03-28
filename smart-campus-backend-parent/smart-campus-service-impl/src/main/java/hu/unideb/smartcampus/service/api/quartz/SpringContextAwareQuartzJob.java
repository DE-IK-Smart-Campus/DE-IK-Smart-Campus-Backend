package hu.unideb.smartcampus.service.api.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class SpringContextAwareQuartzJob implements Job, ApplicationContextAware {

  protected static ApplicationContext ctx;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.ctx = applicationContext;
  }

  @Override
  public final void execute(JobExecutionContext context) throws JobExecutionException {
    ctx.getAutowireCapableBeanFactory().autowireBean(this);

    executeInternal(context);
  }

  protected abstract void executeInternal(JobExecutionContext context);

}

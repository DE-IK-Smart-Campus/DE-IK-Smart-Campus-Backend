package hu.unideb.smartcampus.service.api.config;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Smart Campus Async Configuration class.
 */
@Configuration
@EnableAsync
public class AsyncConfiguration extends AsyncConfigurerSupport {

  private static final Logger LOGGER = LoggerFactory.getLogger(AsyncConfiguration.class);

  private static final String PREFIX = "SmartCampusNeptun-";

  @Override
  public Executor getAsyncExecutor() {
    LOGGER.debug("Async task executer creation called.");
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(5);
    executor.setMaxPoolSize(5);
    executor.setQueueCapacity(500);
    executor.setThreadNamePrefix(PREFIX);
    executor.initialize();
    LOGGER.debug("Async task executer initialized.");
    return executor;
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return new SimpleAsyncUncaughtExceptionHandler();
  }
}

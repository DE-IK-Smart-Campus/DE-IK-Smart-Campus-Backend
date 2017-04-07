package hu.unideb.smartcampus.service.api.xmpp;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.service.api.quartz.SpringContextAwareQuartzJob;

@Component
public class XmppLoginJob extends SpringContextAwareQuartzJob {

  private static final Logger LOGGER = LoggerFactory.getLogger(XmppLoginJob.class);

  @Autowired
  private DefaultUser defaultUser;

  @Override
  protected void executeInternal(JobExecutionContext context) {
    if (defaultUser.isDisconnected()) {
      defaultUser.reconnect();
      LOGGER.warn("Default user reconnected");
    } else {
      LOGGER.debug("Default user connected, done nothing");
    }
  }
  
}

package hu.unideb.smartcampus.service.api.rss;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import hu.unideb.smartcampus.service.api.context.FacebookClient;
import hu.unideb.smartcampus.service.api.context.FacebookTokenContext;
import hu.unideb.smartcampus.service.api.quartz.SpringContextAwareQuartzJob;
import hu.unideb.smartcampus.service.api.rss.facebook.FacebookEventResult;

public class RssPollerQuartzJob extends SpringContextAwareQuartzJob {

  private static final Logger LOGGER = LoggerFactory.getLogger(RssPollerQuartzJob.class);

  @Autowired
  private FacebookTokenContext tokenContext;

  @Autowired
  private FacebookClient facebookClient;
  
  @Override
  protected void executeInternal(JobExecutionContext context) {
    if (!tokenContext.isValid()){
      tokenContext.revalidate();
    }

    final String token = tokenContext.getToken();
    
    FacebookEventResult facebookEventByPageId = facebookClient.getFacebookEventByPageId("202064123149352", token);
    
    
  }

}

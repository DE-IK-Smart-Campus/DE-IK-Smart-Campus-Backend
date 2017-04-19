package hu.unideb.smartcampus.service.api.rss;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.event.service.internal.EventListenerServiceInitiator;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import hu.unideb.smartcampus.service.api.context.FacebookClient;
import hu.unideb.smartcampus.service.api.context.FacebookTokenContext;
import hu.unideb.smartcampus.service.api.quartz.SpringContextAwareQuartzJob;
import hu.unideb.smartcampus.service.api.rss.facebook.FacebookEvent;
import hu.unideb.smartcampus.service.api.rss.facebook.FacebookEventResult;
import hu.unideb.smartcampus.service.api.rss.facebook.FacebookPage;
import hu.unideb.smartcampus.service.api.rss.facebook.FacebookPageLikes;
import hu.unideb.smartcampus.shared.iq.request.EventListingIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.EventIqElement;

public class RssPollerQuartzJob extends SpringContextAwareQuartzJob {

  private static final Logger LOGGER = LoggerFactory.getLogger(RssPollerQuartzJob.class);

  @Autowired
  private EventProvider eventProvider;

  @Autowired
  private EventToEventIqElementConverter eventConveter;
  
  @Override
  protected void executeInternal(JobExecutionContext context) {

    List<Event> events = eventProvider.getEventsBetween(1491116038L, 1492116038L);
    EventListingIqRequest request = new EventListingIqRequest();
    List<EventIqElement> result = new LinkedList<>();
    for (Event event:events){
      result.add(eventConveter.convert(event));
    }
    request.setEvents(result);
    
    LOGGER.info(request.toXML().toString());
    LOGGER.info("Események száma: {}", events.size());

  }

}

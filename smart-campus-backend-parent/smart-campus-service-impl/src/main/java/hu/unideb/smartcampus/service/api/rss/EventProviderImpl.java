package hu.unideb.smartcampus.service.api.rss;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.context.FacebookClient;
import hu.unideb.smartcampus.service.api.rss.facebook.FacebookEvent;
import hu.unideb.smartcampus.service.api.rss.facebook.FacebookEventResult;

@Service
public class EventProviderImpl implements EventProvider{

  @Autowired
  private FacebookClient facebookClient; 
  
  @Autowired
  private FacebookPageIdProvider pageIdProvider; 
  
  @Autowired
  private FacebookEventConverter eventConverter;
  
  @Override
  public List<Event> getEventsBetween(Long since, Long until) {
    Set<String> pageIds = pageIdProvider.getFacebookIds();
    
    List<Event> result = new ArrayList<>();
    
    for(String pageId:pageIds){
      FacebookEventResult eventResult= facebookClient.getFacebookEventByPageId(pageId, since, until);
      for(FacebookEvent event :eventResult.getData()){
        result.add(eventConverter.convert(event));
      }
    }
    
    return result;
  }

}

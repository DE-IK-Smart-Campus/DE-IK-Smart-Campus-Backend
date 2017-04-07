package hu.unideb.smartcampus.service.api.rss;

import java.util.List;

import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.rss.Event;
import hu.unideb.smartcampus.service.api.rss.EventProvider;

@Service
public class EventProviderImpl implements EventProvider{

  @Override
  public List<Event> getNonExistingEvents() {
    return null;
  }

}

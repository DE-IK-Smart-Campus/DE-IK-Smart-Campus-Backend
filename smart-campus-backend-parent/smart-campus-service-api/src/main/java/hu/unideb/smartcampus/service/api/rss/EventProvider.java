package hu.unideb.smartcampus.service.api.rss;

import java.util.List;

public interface EventProvider {

  List<Event> getNonExistingEvents();
  
}

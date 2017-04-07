package hu.unideb.smartcampus.service.api.rss;

import java.util.List;

public interface PagableEventProvider {

  List<Event> getFromPage(String page);
  
  String nextPage(String page);
  
}

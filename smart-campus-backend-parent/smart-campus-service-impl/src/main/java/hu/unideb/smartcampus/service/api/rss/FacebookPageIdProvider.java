package hu.unideb.smartcampus.service.api.rss;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.service.api.context.FacebookClient;
import hu.unideb.smartcampus.service.api.rss.facebook.FacebookPage;

@Component
public class FacebookPageIdProvider {

  @Autowired
  private FacebookClient facebookClient;
  
  public Set<String> getFacebookIds(){
    final String token = facebookClient.getNewToken();
    List<FacebookPage> data = facebookClient.getLikedPages().getData();
    return data.stream().map((FacebookPage page)->page.getId()).collect(Collectors.toSet());
  }
  
}

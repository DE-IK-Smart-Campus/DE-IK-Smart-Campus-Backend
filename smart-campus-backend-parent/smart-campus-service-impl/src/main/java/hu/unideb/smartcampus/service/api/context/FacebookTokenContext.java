package hu.unideb.smartcampus.service.api.context;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = FacebookTokenContext.SCOPE_SINGLETON)
public class FacebookTokenContext {

  public static final String SCOPE_SINGLETON = "singleton";

  private String token;
  
  @Autowired
  private FacebookClient facebookClient;
  
  @PostConstruct
  private void init(){
    revalidate();
  }
  
  public boolean isValid(){
    return facebookClient.isTokenValid(token);
  }

  public void revalidate() {
    this.token = facebookClient.getNewToken();
  }

  public String getToken() {
    return token;
  }

}

package hu.unideb.smartcampus.web.config.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.util.UrlPathHelper;

public class SmartCampusApiRequestMatcher implements RequestMatcher {

  protected String apiPath;

  public SmartCampusApiRequestMatcher(String apiPath) {
    super();
    this.apiPath = apiPath;
  }

  @Override
  public boolean matches(HttpServletRequest request) {
    String path = new UrlPathHelper().getPathWithinApplication(request);
    return path.startsWith(apiPath);
  }

}

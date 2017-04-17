package hu.unideb.smartcampus.shared.facebook;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FacebookApiProperties {

  public static final String SINCE = "since";

  public static final String LIMIT = "limit";

  public static final String CLIENT_CREDENTIALS = "client_credentials";

  public static final String GRANT_TYPE = "grant_type";

  public static final String ACCESS_TOKEN = "access_token";

  public static final String INPUT_TOKEN = "input_token";

  public static final String TOKEN_REFRESH_PATH = "/oauth/access_token";

  public static final String GRAPH_FACEBOOK_HOST = "https://graph.facebook.com";

  public static final String PAGE_ID = "pageId";
  
  public static final String UNTIL = "until";

  public static final String TOKEN = "token";

  public static final String CLIENT_ID = "client_id";

  public static final String CLIENT_SERCRET = "client_secret";

  public static final String EVENTS_OF_PAGE = "/v2.8/{" + PAGE_ID + "}/events";

  public static final String CHECK_TOKEN_PATH = "/debug_token";

  public static final String LIKED_PAGES = "/v2.8/{" + PAGE_ID + "}/likes";

}

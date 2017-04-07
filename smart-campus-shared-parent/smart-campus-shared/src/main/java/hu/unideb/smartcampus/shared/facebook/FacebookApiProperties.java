package hu.unideb.smartcampus.shared.facebook;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FacebookApiProperties {

  public static final String SINCE = "since";

  public static final String PAGE_ID = "pageId";

  public static final String TOKEN = "token";
  
  public static final String CLIENT_ID = "clientId";

  public static final String CLIENT_SERCRET = "clientSercret";
  
  public static final String CHECK_TOKEN_PATH =
      "https://graph.facebook.com/debug_token?input_token={" + TOKEN + "}&access_token={" + TOKEN
          + "}";


  public static final String TOKEN_REFRESH_PATH = "https://graph.facebook.com/oauth/access_token?client_id={"+CLIENT_ID+"}&client_secret={"+CLIENT_SERCRET+"}&grant_type=client_credentials";
  
  public static final String EVENT_LIST = "https://graph.facebook.com/v2.8/{" + PAGE_ID + "}/events?access_token={"+TOKEN+"}&limit=10000&since={" + SINCE + "}";
  
}

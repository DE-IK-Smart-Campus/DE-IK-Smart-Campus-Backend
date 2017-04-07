package hu.unideb.smartcampus.service.api.context;

import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.CHECK_TOKEN_PATH;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.EVENT_LIST;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.CLIENT_ID;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.CLIENT_SERCRET;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.PAGE_ID;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.TOKEN;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.TOKEN_REFRESH_PATH;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.SINCE;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;

import hu.unideb.smartcampus.service.api.UrlParameterSubstitutor;
import hu.unideb.smartcampus.service.api.rss.facebook.FacebookEvent;
import hu.unideb.smartcampus.service.api.rss.facebook.FacebookEventResult;
import hu.unideb.smartcampus.service.api.rss.facebook.credentials.FacebookProperty;
import hu.unideb.smartcampus.service.api.rss.facebook.credentials.FacebookPropertyProvider;


@Component
public class FacebookClient {

  @Autowired
  private UrlParameterSubstitutor urlParameterSubstitutor;

  @Autowired
  private FacebookPropertyProvider facebookPropertyProvider;

  public boolean isTokenValid(String token) {
    Map<String, String> content = ImmutableMap.<String, String>builder().put(TOKEN, token).build();
    final String fullPath = urlParameterSubstitutor.substitute(CHECK_TOKEN_PATH, content);
    Client client = ClientBuilder.newClient();
    FacebookTokenCheck facebookTokenCheck =
        client.target(fullPath).request(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON_TYPE).get(FacebookTokenCheck.class);

    return facebookTokenCheck.getData().getValid();
  }

  public String getNewToken() {

    Map<String, String> content = ImmutableMap.<String, String>builder()
        .put(CLIENT_ID, facebookPropertyProvider.getProperty(FacebookProperty.CLIENT_ID))
        .put(CLIENT_SERCRET,
            facebookPropertyProvider.getProperty(FacebookProperty.CLIENT_SECRET_ID))
        .build();
    final String fullPath = urlParameterSubstitutor.substitute(TOKEN_REFRESH_PATH, content);
    Client client = ClientBuilder.newClient();
    FacebookAccessTokenRenewal accessTokenRenewal =
        client.target(fullPath).request(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON_TYPE).get(FacebookAccessTokenRenewal.class);

    return accessTokenRenewal.getAccessToken();
  }

  public FacebookEventResult getFacebookEventByPageId(String pageId, String token) {
    Map<String, String> content = ImmutableMap.<String, String>builder().put(TOKEN, token)
        .put(PAGE_ID, pageId).put(SINCE, Long.toString(Instant.now().getEpochSecond())).build();
    final String fullPath = urlParameterSubstitutor.substitute(EVENT_LIST, content);
    Client client = ClientBuilder.newClient();
    FacebookEventResult accessTokenRenewal =
        client.target(fullPath).request(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON_TYPE).get(FacebookEventResult.class);
    return accessTokenRenewal;
  }

}

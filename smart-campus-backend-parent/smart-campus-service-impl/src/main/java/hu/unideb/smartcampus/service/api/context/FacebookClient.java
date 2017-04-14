package hu.unideb.smartcampus.service.api.context;

import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.ACCESS_TOKEN;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.CHECK_TOKEN_PATH;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.UNTIL;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.LIKED_PAGES;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.CLIENT_CREDENTIALS;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.CLIENT_ID;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.CLIENT_SERCRET;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.EVENTS_OF_PAGE;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.GRANT_TYPE;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.GRAPH_FACEBOOK_HOST;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.INPUT_TOKEN;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.LIMIT;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.PAGE_ID;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.SINCE;
import static hu.unideb.smartcampus.shared.facebook.FacebookApiProperties.TOKEN_REFRESH_PATH;

import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;

import hu.unideb.smartcampus.service.api.rss.facebook.FacebookEventResult;
import hu.unideb.smartcampus.service.api.rss.facebook.FacebookPageLikes;
import hu.unideb.smartcampus.service.api.rss.facebook.credentials.FacebookProperty;
import hu.unideb.smartcampus.service.api.rss.facebook.credentials.FacebookPropertyProvider;


@Component
public class FacebookClient {

  private static final Long PAGE_SIZE = 10000L;

  @Autowired
  private FacebookPropertyProvider facebookPropertyProvider;

  public boolean isTokenValid(String token) {
    Client client = ClientBuilder.newClient();
    FacebookTokenCheck facebookTokenCheck =
        client.target(buildUrl(CHECK_TOKEN_PATH)).queryParam(ACCESS_TOKEN, token)
            .queryParam(INPUT_TOKEN, token).request(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON_TYPE).get(FacebookTokenCheck.class);

    return facebookTokenCheck.getData().getValid();
  }

  public String getNewToken() {

    Client client = ClientBuilder.newClient();
    FacebookAccessTokenRenewal accessTokenRenewal = client.target(buildUrl(TOKEN_REFRESH_PATH))
        .queryParam(CLIENT_ID, facebookPropertyProvider.getProperty(FacebookProperty.CLIENT_ID))
        .queryParam(CLIENT_SERCRET,
            facebookPropertyProvider.getProperty(FacebookProperty.CLIENT_SECRET_ID))
        .queryParam(GRANT_TYPE, CLIENT_CREDENTIALS).request(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON_TYPE).get(FacebookAccessTokenRenewal.class);

    return accessTokenRenewal.getAccessToken();
  }

  public FacebookEventResult getFacebookEventByPageId(String pageId, Long since, Long until) {
    final String token = getNewToken();
    Map<String, String> content =
        ImmutableMap.<String, String>builder().put(PAGE_ID, pageId).build();
    Client client = ClientBuilder.newClient();
    FacebookEventResult accessTokenRenewal = client.target(buildUrl(EVENTS_OF_PAGE, content))
        .queryParam(ACCESS_TOKEN, token).queryParam(LIMIT, PAGE_SIZE).queryParam(SINCE, since)
        .queryParam(UNTIL, until).request(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON_TYPE).get(FacebookEventResult.class);
    return accessTokenRenewal;
  }

  public FacebookPageLikes getLikedPages() {
    final String token = getNewToken();
    Map<String, String> content = ImmutableMap.<String, String>builder()
        .put(PAGE_ID, facebookPropertyProvider.getProperty(FacebookProperty.PAGE_ID)).build();
    Client client = ClientBuilder.newClient();
    FacebookPageLikes accessTokenRenewal =
        client.target(buildUrl(LIKED_PAGES, content)).queryParam(ACCESS_TOKEN, token)
            .queryParam(LIMIT, PAGE_SIZE).request(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON_TYPE).get(FacebookPageLikes.class);
    return accessTokenRenewal;
  }

  private String buildUrl(String path) {
    return GRAPH_FACEBOOK_HOST + path;
  }

  private String buildUrl(String path, Map<String, String> pathParams) {
    StrSubstitutor strSubstitutor = new StrSubstitutor(pathParams, "{", "}");
    return strSubstitutor.replace(buildUrl(path));
  }

}

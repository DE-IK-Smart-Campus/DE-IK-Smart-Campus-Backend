package hu.unideb.smartcampus.webservice.api.neptun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Neptun token service implementation.
 */
@Service
public class NeptunTokenServiceImpl implements NeptunTokenService {

  private static final Logger LOGGER = LoggerFactory.getLogger(NeptunTokenServiceImpl.class);

  /**
   * Client secret code.
   */
  private static final String CLIENT_SECRET = "client_secret";

  /**
   * Client ID.
   */
  private static final String CLIENT_ID = "client_id";

  /**
   * Grant type.
   */
  private static final String GRANT_TYPE = "grant_type";

  /**
   * Client secret value.
   */
  @Resource(lookup = "java:global/neptun.client.secret.value")
  private String CLIENT_SECRET_VALUE;

  /**
   * Client ID value.
   */
  @Resource(lookup = "java:global/neptun.client.id")
  private String CLIENT_ID_VALUE;

  /**
   * Grant tpye value.
   */
  @Resource(lookup = "java:global/neptun.grant.type")
  private String GRANT_TYPE_VALUE;

  /**
   * URL for Neptun endpoint.
   */
  @Resource(lookup = "java:global/neptun.url")
  private String URL;

  /**
   * Token endpoint.
   */
  @Resource(lookup = "java:global/neptun.token.endpoint")
  private String TOKEN_ENDPOINT;

  /**
   * {@inheritDoc}.
   */
  @Override
  public String getAccessToken() throws IOException {
    LOGGER.info("Generating new token for Neptun REST access.");
    HttpPost post = new HttpPost(URL + TOKEN_ENDPOINT);
    List<NameValuePair> params = createParams();
    post.setEntity(createParams(params));
    HttpClient client = createClient();
    HttpResponse response = client.execute(post);
    BufferedReader rd = createReaderByResponse(response);
    String string = readResult(rd);
    AccessToken parsedObject = getAccessTokenObject(string);
    return parsedObject.getAccessToken();
  }

  private CloseableHttpClient createClient() {
    return HttpClientBuilder.create()
        .setSSLHostnameVerifier(new NoopHostnameVerifier())
        .build();
  }

  private List<NameValuePair> createParams() {
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair(GRANT_TYPE, GRANT_TYPE_VALUE));
    params.add(new BasicNameValuePair(CLIENT_ID, CLIENT_ID_VALUE));
    params.add(new BasicNameValuePair(CLIENT_SECRET, CLIENT_SECRET_VALUE));
    return params;
  }

  private UrlEncodedFormEntity createParams(List<NameValuePair> params)
      throws UnsupportedEncodingException {
    return new UrlEncodedFormEntity(params);
  }

  private BufferedReader createReaderByResponse(HttpResponse response) throws IOException {
    return new BufferedReader(
        new InputStreamReader(response.getEntity().getContent()));
  }

  private AccessToken getAccessTokenObject(String string)
      throws IOException, JsonParseException, JsonMappingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(string, AccessToken.class);
  }

  private String readResult(BufferedReader rd) throws IOException {
    String line;
    StringBuilder result = new StringBuilder();
    while ((line = rd.readLine()) != null) {
      result.append(line);
    }
    return result.toString();
  }

}

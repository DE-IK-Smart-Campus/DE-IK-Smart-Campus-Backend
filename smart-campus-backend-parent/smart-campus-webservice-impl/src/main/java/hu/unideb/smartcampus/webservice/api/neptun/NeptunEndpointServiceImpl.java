package hu.unideb.smartcampus.webservice.api.neptun;

import java.io.IOException;

import javax.annotation.Resource;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation for neptun endpoint service.
 */
@Service
public class NeptunEndpointServiceImpl implements NeptunEndpointService {

  private static final String STUDENT_TIMETABLE_BY_NEPTUN_IDENTIFIER =
      "/studentTimetable/neptunIdentifier/";

  private static final String NEPTUN_INFO_BY_NEPTUN_IDENTIFIER = "/neptunInfo/neptunIdentifier/";

  private static final String NEPTUN_INFO_BY_UID = "/neptunInfo/uid/";

  private static final Logger LOGGER = LoggerFactory.getLogger(NeptunEndpointServiceImpl.class);

  private static final String PATH = "/eduid/rest";

  @Resource(lookup = "java:global/neptun.url")
  private String neptunUrl;

  @Autowired
  private NeptunTokenService neptunTokenService;

  /**
   * {@inheritDoc}.
   */
  @Override
  public NeptunInfo getNeptunInfoByNeptunIdentifier(String neptunIdentifier) throws IOException {
    LOGGER.info("Requesting Neptun info by identifier: {}", neptunIdentifier);
    Client client = createClient();
    WebTarget target =
        client.target(getUrl(NEPTUN_INFO_BY_NEPTUN_IDENTIFIER, neptunIdentifier));
    return target.request().get(NeptunInfo.class);
  }


  /**
   * {@inheritDoc}.
   */
  @Override
  public NeptunInfo getNeptunInfoByUid(String uid) throws IOException {
    LOGGER.info("Requesting Neptun info for {}", uid);
    Client client = createClient();
    WebTarget target = client.target(getUrl(NEPTUN_INFO_BY_UID, uid));
    NeptunInfo neptunInfo = target.request().get(NeptunInfo.class);
    return neptunInfo;
  }


  /**
   * {@inheritDoc}.
   */
  @Override
  public StudentTimeTable getStudentTimetable(String neptunIdentifier) throws IOException {
    LOGGER.info("Requesting student time table by identifier {}", neptunIdentifier);
    Client client = createClient();
    WebTarget target =
        client.target(getUrl(STUDENT_TIMETABLE_BY_NEPTUN_IDENTIFIER, neptunIdentifier));
    return target.request().get(StudentTimeTable.class);
  }

  private Client createClient() throws IOException {
    ClientBuilder builder = ClientBuilder.newBuilder().hostnameVerifier(new NoopHostnameVerifier());
    return builder.build()
        .register(
            Authenticator
                .builder()
                .token(getToken())
                .build());
  }

  private String getToken() throws IOException {
    return neptunTokenService.getAccessToken();
  }

  private String getUrl(String endpoint, String uid) {
    return neptunUrl + PATH + endpoint + uid;
  }

}

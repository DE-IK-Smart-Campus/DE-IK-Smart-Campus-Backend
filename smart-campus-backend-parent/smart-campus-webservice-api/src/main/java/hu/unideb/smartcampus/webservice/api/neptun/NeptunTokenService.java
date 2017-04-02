package hu.unideb.smartcampus.webservice.api.neptun;

import java.io.IOException;

/**
 * Neptun token service.
 */
public interface NeptunTokenService {

  /**
   * Get a new access token to Neptun REST auth.
   */
  String getAccessToken() throws IOException;
  
}

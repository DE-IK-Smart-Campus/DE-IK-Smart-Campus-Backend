package hu.unideb.smartcampus.service.api;

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

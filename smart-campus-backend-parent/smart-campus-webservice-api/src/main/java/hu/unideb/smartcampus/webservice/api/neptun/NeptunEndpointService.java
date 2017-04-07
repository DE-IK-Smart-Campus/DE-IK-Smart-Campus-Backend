package hu.unideb.smartcampus.webservice.api.neptun;

import java.io.IOException;

/**
 * Service for Neptun endpoint.
 */
public interface NeptunEndpointService {

  /**
   * Get neptun info by UID.
   * 
   * @param uid neptun UID.
   */
  String getNeptunInfoByUid(String uid) throws IOException;

  /**
   * Get neptun info by neptun identifier.
   * 
   * @param neptunIdentifier neptun identifier.
   */
  String getNeptunInfoByNeptunIdentifier(String neptunIdentifier) throws IOException;

  /**
   * Get student timetable in string.
   */
  String getStudentTimetable(String neptunIdentifier) throws IOException;
}

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
  NeptunInfo getNeptunInfoByUid(String uid) throws IOException;

  /**
   * Get neptun info by neptun identifier.
   * 
   * @param neptunIdentifier neptun identifier.
   */
  NeptunInfo getNeptunInfoByNeptunIdentifier(String neptunIdentifier) throws IOException;

  /**
   * Get student timetable in string.
   */
  StudentTimeTable getStudentTimetable(String neptunIdentifier) throws IOException;
}

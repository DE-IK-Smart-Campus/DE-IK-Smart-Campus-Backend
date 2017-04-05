package hu.unideb.smartcampus.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.webservice.api.neptun.NeptunEndpointService;

/**
 * Neptun REST controller.
 */
@RestController
@RequestMapping("/neptun")
public class NeptunRestController {

  /**
   * Neptun endpoint service.
   */
  @Autowired
  private NeptunEndpointService neptunEndpointService;

  /**
   * Get neptun info by UID.
   */
  @GetMapping("/neptunInfo/uid/{uid}")
  public ResponseEntity<String> getNeptunInfoByUid(@PathVariable("uid") String uid) {
    ResponseEntity<String> ret;
    String result;
    try {
      result = neptunEndpointService.getNeptunInfoByUid(uid);
      ret = ResponseEntity.ok(result);
    } catch (IOException e) {
      result = e.getCause().getMessage();
      ret = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(result);
    }
    return ret;
  }

  /**
   * Get neptun info by identifier.
   */
  @GetMapping("/neptunInfo/identifier/{identifier}")
  public ResponseEntity<String> getNeptunInfoByIdentifier(
      @PathVariable("identifier") String identifier) {
    ResponseEntity<String> ret;
    String result;
    try {
      result = neptunEndpointService.getNeptunInfoByNeptunIdentifier(identifier);
      ret = ResponseEntity.ok(result);
    } catch (IOException e) {
      result = e.getCause().getMessage();
      ret = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(result);
    }
    return ret;
  }

  /**
   * Get time table.
   */
  @GetMapping("/timetable/{identifier}")
  public ResponseEntity<String> getStudentTimetable(@PathVariable("identifier") String identifier) {
    ResponseEntity<String> ret;
    String result;
    try {
      result = neptunEndpointService.getStudentTimetable(identifier);
      ret = ResponseEntity.ok(result);
    } catch (IOException e) {
      result = e.getCause().getMessage();
      ret = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(result);
    }
    return ret;
  }

}

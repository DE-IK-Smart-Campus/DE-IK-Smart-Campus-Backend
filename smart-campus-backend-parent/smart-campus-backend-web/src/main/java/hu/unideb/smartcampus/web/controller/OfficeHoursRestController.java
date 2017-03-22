package hu.unideb.smartcampus.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.service.api.ConsultingHourService;
import hu.unideb.smartcampus.shared.requestmessages.CreateOfficeHoursRequest;


/**
 * Office hours rest controller.
 */
@RestController
public class OfficeHoursRestController {

  /**
   * Consulting hour service.
   */
  private final ConsultingHourService consultingHourService;

  /**
   * Constructor for dependency injection.
   */
  @Autowired
  public OfficeHoursRestController(final ConsultingHourService consultingHourService) {
    this.consultingHourService = consultingHourService;
  }

  /**
   * Generate office hours by request.
   */
  @PostMapping(path = "/officehours", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity postOfficeHours(CreateOfficeHoursRequest request) {
    consultingHourService.generateOfficeHoursForInstructor(request.getInstructorId(),
        request.getOfficeHours(), request.getIntervall());
    return ResponseEntity.ok("Office hours created.");
  }
}

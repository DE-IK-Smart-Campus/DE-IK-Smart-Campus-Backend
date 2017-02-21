package hu.unideb.smartcampus.web.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.service.ejabberd.SharedRosterService;

/**
 * SharedRosterService endpoint.
 */
@RestController
public class SharedRosterGroupRestController {

  /**
   * SharedRosterService dependency.
   */
  private final SharedRosterService sharedRosterGroup;

  /**
   * Constructor for dependency injection.
   */
  @Autowired
  public SharedRosterGroupRestController(final SharedRosterService sampleService) {
    this.sharedRosterGroup = sampleService;
  }

  /**
   * Get group list.
   *
   * @return {@link ResponseEntity}
   */
  @GetMapping(path = "/groups")
  public ResponseEntity<List<String>> getGroupList() {
    return ResponseEntity.ok(sharedRosterGroup.getGroupList());
  }
}

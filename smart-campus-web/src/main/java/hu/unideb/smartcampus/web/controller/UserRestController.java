package hu.unideb.smartcampus.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.exception.XmppException;


/**
 * Sample rest controller.
 */
@RestController
public class UserRestController {

  /**
   * Sample dependency.
   */
  private final EjabberdUser ejabberdUser;

  /**
   * Constructor for dependency injection.
   */
  @Autowired
  public UserRestController(final EjabberdUser sampleService) {
    this.ejabberdUser = sampleService;
  }

  /**
   * Sample endpoint.
   * @return {@link ResponseEntity}
   */
  @GetMapping(path = "/login")
  public ResponseEntity login(@RequestParam(name = "user") String user,
                              @RequestParam(name = "pass") String password) {
    ResponseEntity<String> body = ResponseEntity.ok().body("OK");
    try {
      ejabberdUser.login(user, password);
    } catch (XmppException e) {
      body = ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
    return body;
  }

  /**
   * Sample endpoint.
   * @return {@link ResponseEntity}
   */
  @GetMapping(path = "/logout")
  public ResponseEntity logout() {
    ejabberdUser.logout();
    return ResponseEntity.ok().body("OK");
  }
}

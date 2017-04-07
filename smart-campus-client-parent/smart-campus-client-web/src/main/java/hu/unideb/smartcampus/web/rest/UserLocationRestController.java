package hu.unideb.smartcampus.web.rest;

import java.security.Principal;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.service.api.UserLocationService;
import hu.unideb.smartcampus.service.api.domain.Location;
import hu.unideb.smartcampus.shared.exception.UserLocationSendFailedException;
import hu.unideb.smartcampus.web.domain.UserLocation;

@RestController
public class UserLocationRestController {

  private static final String LOCATION_SENT_SESSION_PARAMETER_NAME = "locationSentEpochMillis";

  @Autowired
  private UserLocationService userLocationService;

  @PostMapping(path = "/userLocation", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public void handleUserLocation(@RequestBody UserLocation userLocation, HttpServletRequest request,
      Principal principal) {

    final Long lastSent = getLastTimeSentFromSession(request);

    if (userLocationService.sendNeeded(Instant.ofEpochMilli(lastSent))) {
      final String username = getUsernameFromPrincipal(principal);

      Location location = Location.builder().accuracy(userLocation.getAccuracy())
          .longitude(userLocation.getLongitude()).latitude(userLocation.getLatitude())
          .timestamp(Instant.now().toEpochMilli()).username(username).build();

      try {
        userLocationService.send(location);
        saveSendTimestampToSession(request, Instant.now().toEpochMilli());
      } catch (UserLocationSendFailedException e) {
        //do nothing
      }

    }

  }

  private String getUsernameFromPrincipal(final Principal principal) {
    return principal.getName();
  }

  private void saveSendTimestampToSession(final HttpServletRequest request, final Long timestamp) {
    request.getSession().setAttribute(LOCATION_SENT_SESSION_PARAMETER_NAME, timestamp);
  }

  private Long getLastTimeSentFromSession(final HttpServletRequest request) {
    Object value = (Long) request.getSession().getAttribute(LOCATION_SENT_SESSION_PARAMETER_NAME);
    return value == null ? Long.MIN_VALUE : (Long) value;
  }

}

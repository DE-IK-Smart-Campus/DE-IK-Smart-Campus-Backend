package hu.unideb.smartcampus.service.api;

import java.time.Instant;

import hu.unideb.smartcampus.service.api.domain.Location;
import hu.unideb.smartcampus.shared.exception.UserLocationSendFailedException;

public interface UserLocationService {

  public boolean sendNeeded(Instant lastSent);

  public void send(Location location) throws UserLocationSendFailedException;
}

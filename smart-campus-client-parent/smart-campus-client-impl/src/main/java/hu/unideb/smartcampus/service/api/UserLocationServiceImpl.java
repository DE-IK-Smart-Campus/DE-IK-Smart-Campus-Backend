package hu.unideb.smartcampus.service.api;

import java.time.Instant;

import org.jivesoftware.smack.packet.IQ.Type;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.domain.Location;
import hu.unideb.smartcampus.service.api.xmpp.XmppConnectedService;
import hu.unideb.smartcampus.shared.exception.InvalidXmppConnectionException;
import hu.unideb.smartcampus.shared.exception.UserLocationSendFailedException;
import hu.unideb.smartcampus.shared.exception.XmppException;
import hu.unideb.smartcampus.shared.iq.request.UserLocationIqRequest;

@Service
public class UserLocationServiceImpl implements UserLocationService {

  private static final long LOCATION_UPDATE_INTERVAL = 300;

  @Autowired
  private XmppConnectedService xmppConnectedService;

  @Override
  public boolean sendNeeded(Instant lastSent) {
    final Instant now = Instant.now();
    return lastSent.plusSeconds(LOCATION_UPDATE_INTERVAL).isBefore(now);
  }

  @Override
  public void send(Location location) throws UserLocationSendFailedException{
    UserLocationIqRequest request = new UserLocationIqRequest();
    request.setUsername(location.getUsername());
    request.setAccuracy(location.getAccuracy());
    request.setLongitude(location.getLongitude());
    request.setLatitude(location.getLatitude());
    request.setTimeStamp(location.getTimestamp());
    request.setType(Type.set);
    
    try {
      xmppConnectedService.sendRequestWithReauthenticate(request);
    } catch (XmppException e) {
      LoggerFactory.getLogger(UserLocationServiceImpl.class).info("ez itt mi a faszM");
    } catch (InvalidXmppConnectionException e) {
      LoggerFactory.getLogger(UserLocationServiceImpl.class).info("ez itt mi a faszM");
    }
  }

}


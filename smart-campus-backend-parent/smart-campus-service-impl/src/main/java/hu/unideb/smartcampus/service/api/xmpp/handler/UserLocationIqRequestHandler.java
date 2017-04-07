package hu.unideb.smartcampus.service.api.xmpp.handler;

import java.util.Optional;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.UserLocationService;
import hu.unideb.smartcampus.service.api.UserService;
import hu.unideb.smartcampus.service.api.domain.User;
import hu.unideb.smartcampus.service.api.domain.UserLocation;
import hu.unideb.smartcampus.shared.exception.XmppException;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.UserLocationIqRequest;

@Service
public class UserLocationIqRequestHandler extends AbstractSmartCampusIqRequestHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserLocationIqRequest.class);

  /**
   * Minimum accuracy in meter.
   */
  private static final Double MINIMUM_LOCAION_ACCURACY = 400D;

  @Autowired
  private UserService userService;

  @Autowired
  private UserLocationService userLocationService;

  public UserLocationIqRequestHandler() {
    super(UserLocationIqRequest.ELEMENT, BaseSmartCampusIqRequest.BASE_NAMESPACE, Type.set,
        Mode.sync);
  }

  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    super.handleIQRequest(iqRequest);
    UserLocationIqRequest request = (UserLocationIqRequest) iqRequest;
    Optional<User> user = userService.getByUsername(request.getUsername());
    if (!user.isPresent()) {
      LOGGER.error("Request recieved from an inconsistent user: {}", request.getUsername());
      request.setType(Type.error);
    }

    if (request.getAccuracy() < MINIMUM_LOCAION_ACCURACY) {
      UserLocation location = UserLocation.builder().accuracy(request.getAccuracy())
          .longitude(request.getLongitude()).latitude(request.getLatitude()).user(user.get())
          .timestamp(request.getTimeStamp()).build();

      userLocationService.save(location);
    }

    return iqRequest;
  }

}

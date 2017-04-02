package hu.unideb.smartcampus.shared.iq.request;

import org.jivesoftware.smack.packet.IQ;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLocationIqRequest extends BaseSmartCampusIqRequest {

  public static final String LONGITUDE = "longitude";

  public static final String LATITUDE = "latitude";

  public static final String TIMESTAMP = "timestamp";

  public static final String ACCURACY = "accuracy";

  public static final String USERNAME = "username";

  public static final String ELEMENT = "userLocation";

  private Long timeStamp;

  private Double longitude;

  private Double latitude;

  private String username;

  private Double accuracy;

  public UserLocationIqRequest(IQ iq) {
    super(iq);
  }

  public UserLocationIqRequest() {
    super(ELEMENT);
  }

  public UserLocationIqRequest(String element) {
    super(element);
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }

  @Override
  protected String toXml() {
    StringBuilder sb = new StringBuilder();
    sb.append(tag(USERNAME, username));
    sb.append(tag(ACCURACY, accuracy));
    sb.append(tag(LATITUDE, latitude));
    sb.append(tag(LONGITUDE, longitude));
    sb.append(tag(TIMESTAMP, timeStamp));
    return sb.toString();
  }

}

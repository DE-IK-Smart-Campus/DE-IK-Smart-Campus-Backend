package hu.unideb.smartcampus.shared.iq.provider;

import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.UserLocationIqRequest;

public class UserLocationIqProvider extends BaseSmartCampusIqProvider<UserLocationIqRequest> {

  @Override
  public UserLocationIqRequest parse(XmlPullParser parser, int initialDepth) throws Exception {
    boolean done = false;

    UserLocationIqRequest toReturn = new UserLocationIqRequest();
    int eventType;
    String text = null;
    while (!done) {
      eventType = parser.next();
      String tagname = parser.getName();
      switch (eventType) {
        case XmlPullParser.TEXT:
          text = parser.getText();
          break;
        case XmlPullParser.END_TAG:
          switch (tagname) {
            case "username":
              toReturn.setUsername(text);
              break;
            case "accuracy":
              toReturn.setAccuracy(Double.parseDouble(text));
              break;
            case "longitude":
              toReturn.setLongitude(Double.parseDouble(text));
              break;
            case "latitude":
              toReturn.setLatitude(Double.parseDouble(text));
              break;
            case "timestamp":
              toReturn.setTimeStamp(Long.parseLong(text));
              break;
            case UserLocationIqRequest.ELEMENT:
              done=true;
              break;
          }
          break;
        default:
          break;
      }
    }
    return toReturn;
  }

  @Override
  public Class<? extends BaseSmartCampusIqRequest> getHandledIq() {
    return UserLocationIqRequest.class;
  }



}

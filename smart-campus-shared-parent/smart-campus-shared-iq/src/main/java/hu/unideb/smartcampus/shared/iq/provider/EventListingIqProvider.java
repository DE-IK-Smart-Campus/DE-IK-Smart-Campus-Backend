package hu.unideb.smartcampus.shared.iq.provider;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.EventListingIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.EventIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.LocationIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.PositionIqElement;

public class EventListingIqProvider extends BaseSmartCampusIqProvider<EventListingIqRequest> {

  private boolean done;

  private List<EventIqElement> events;

  private EventIqElement event;
  private PositionIqElement position;
  private LocationIqElement location;

  @Override
  public EventListingIqRequest parse(XmlPullParser parser, int initialDepth) throws Exception {
    EventListingIqRequest request = new EventListingIqRequest();
    done = false;
    int eventType = parser.getEventType();
    String text = "";
    while (!done) {
      eventType = parser.next();
      String tagname = parser.getName();
      switch (eventType) {
        case XmlPullParser.START_TAG:
          switch (tagname) {
            case EventListingIqRequest.TAG_NAME_EVENTS:
              events = new ArrayList<>();
              request.setEvents(events);
              break;
            case EventIqElement.TAG_NAME_EVENT:
              event = new EventIqElement();
              break;
            case LocationIqElement.TAG_NAME_LOCATION:
              location = new LocationIqElement();
              break;
            case PositionIqElement.TAG_NAME_POSITION:
              position = new PositionIqElement();
              break;
          }
          break;
        case XmlPullParser.TEXT:
          text = parser.getText();
          break;
        case XmlPullParser.END_TAG:
          switch (tagname){
            case EventListingIqRequest.ELEMENT:
              done = true;
              break;
            case EventListingIqRequest.TAG_NAME_SINCE:
              request.setSince(Long.parseLong(text));
              break;
            case EventListingIqRequest.TAG_NAME_UNTIL:
              request.setUntil(Long.parseLong(text));
              break;
            case EventIqElement.TAG_NAME_DESCRIPTION:
              event.setDescription(text);
              break;
            case EventIqElement.TAG_NAME_EVENT:
              events.add(event);
              break;
            case EventIqElement.TAG_NAME_NAME:
              event.setName(text);
              break;
            case EventIqElement.TAG_NAME_START_TIME:
              event.setStartTime(Long.parseLong(text));
              break;
            case EventIqElement.TAG_NAME_END_TIME:
              event.setEndTime(Long.parseLong(text));
              break;
            case EventIqElement.TAG_NAME_EVENT_ID:
              event.setEventId(text);
              break;
            case LocationIqElement.TAG_NAME_LOCATION:
              event.setLocation(location);
              break;
            case LocationIqElement.TAG_NAME_NAME:
              location.setName(text);
              break;
            case LocationIqElement.TAG_NAME_ID:
              location.setId(text);
              break;
            case PositionIqElement.TAG_NAME_POSITION:
              location.setPosition(position);
              break;
            case PositionIqElement.TAG_NAME_CITY:
              position.setCity(text);
              break;
            case PositionIqElement.TAG_NAME_COUNTRY:
              position.setCountry(text);
              break;
            case PositionIqElement.TAG_NAME_LATITUDE:
              position.setLatitude(Double.parseDouble(text));
              break;
            case PositionIqElement.TAG_NAME_LONGITUDE:
              position.setLongitude(Double.parseDouble(text));
              break;
            case PositionIqElement.TAG_NAME_STREET:
              position.setStreet(text);
              break;
            case PositionIqElement.TAG_NAME_ZIPCODE:
              position.setZipCode(text);
              break;
          }
          break;
        default:
          break;
      }
    }
    return request;
  }

  @Override
  public Class<? extends BaseSmartCampusIqRequest> getHandledIq() {
    return EventListingIqRequest.class;
  }

}

package hu.unideb.smartcampus.shared.iq.request;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.provider.IQProvider;
import org.junit.Before;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.provider.EventListingIqProvider;
import hu.unideb.smartcampus.shared.iq.request.element.EventIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.LocationIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.PositionIqElement;

public class EventListingIqParserTest extends AbstractParserTest{

  private EventListingIqRequest request;
  
  @Before
  public void before(){
    request = new EventListingIqRequest();
    request.setSince(20L);
    request.setUntil(100L);
    
    List<EventIqElement> events = new ArrayList<>();
    
    EventIqElement event1 = new EventIqElement();
    event1.setDescription("event 1 description");
    event1.setEndTime(System.currentTimeMillis()+100);
    event1.setStartTime(System.currentTimeMillis());
    event1.setName("event 1 name");
    event1.setEventId("event 1 id");
    LocationIqElement location1 = new LocationIqElement();
    location1.setId("location 1 id");
    location1.setName("location 1 name");
    PositionIqElement position1 = new PositionIqElement();
    position1.setCity("position 1 city");
    position1.setCountry("position 1 country");
    position1.setLatitude(10D);
    position1.setLongitude(30D);
    position1.setStreet("position 1 street");
    position1.setZipCode("position 1 zipcode");
    location1.setPosition(position1);
    event1.setLocation(location1);
    events.add(event1);
    
    EventIqElement event2 = new EventIqElement();
    event2.setDescription("event 1 description");
    event2.setEndTime(System.currentTimeMillis()+100);
    event2.setStartTime(System.currentTimeMillis());
    event2.setName("event 1 name");
    event2.setEventId("event 1 id");
    LocationIqElement location2 = new LocationIqElement();
    location2.setId("location 1 id");
    location2.setName("location 1 name");
    PositionIqElement position2 = new PositionIqElement();
    position2.setCity("position 1 city");
    position2.setCountry("position 1 country");
    position2.setLatitude(10D);
    position2.setLongitude(30D);
    position2.setStreet("position 1 street");
    position2.setZipCode("position 1 zipcode");
    location2.setPosition(position2);
    event2.setLocation(location1);
    events.add(event1);
    
    request.setEvents(events);
  }
  
  @Test
  public void testParser(){
    try {
      EventListingIqRequest parsed = getParsedObject(request);
      System.out.println(parsed.toXml());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public String getElement() {
    return EventListingIqRequest.ELEMENT;
  }

  @Override
  public IQProvider getProvider() {
    return new EventListingIqProvider();
  }

}

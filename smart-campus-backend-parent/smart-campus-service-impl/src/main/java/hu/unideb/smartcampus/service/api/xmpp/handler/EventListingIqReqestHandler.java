package hu.unideb.smartcampus.service.api.xmpp.handler;

import java.util.LinkedList;
import java.util.List;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.rss.Event;
import hu.unideb.smartcampus.service.api.rss.EventProvider;
import hu.unideb.smartcampus.service.api.rss.EventToEventIqElementConverter;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.EventListingIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.EventIqElement;

@Service
public class EventListingIqReqestHandler extends AbstractSmartCampusIqRequestHandler {

  @Autowired
  private EventProvider eventProvider;
  
  @Autowired
  private EventToEventIqElementConverter eventConveter;
  
  public EventListingIqReqestHandler() {
    super(EventListingIqRequest.ELEMENT, BaseSmartCampusIqRequest.BASE_NAMESPACE, Type.get,
        Mode.sync);
  }

  public EventListingIqReqestHandler(String element, String namespace, Type type, Mode mode) {
    super(element, namespace, type, mode);
  }

  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    EventListingIqRequest request = (EventListingIqRequest) super.handleIQRequest(iqRequest);
    
    List<Event> events = eventProvider.getEventsBetween(request.getSince(), request.getUntil());
    
    List<EventIqElement> resultList = new LinkedList<>();
    for(Event event:events){
      resultList.add(eventConveter.convert(event));
    }
    
    
    request.setEvents(resultList);
    
    return request;
  }


}

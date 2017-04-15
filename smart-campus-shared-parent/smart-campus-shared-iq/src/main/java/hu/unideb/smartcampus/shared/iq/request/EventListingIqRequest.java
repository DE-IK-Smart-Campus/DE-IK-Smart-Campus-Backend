package hu.unideb.smartcampus.shared.iq.request;

import java.util.Collections;
import java.util.List;

import org.jivesoftware.smack.packet.IQ;

import hu.unideb.smartcampus.shared.iq.request.element.EventIqElement;

public class EventListingIqRequest extends BaseSmartCampusIqRequest {

  public static final String TAG_NAME_SINCE = "since";
  public static final String TAG_NAME_UNTIL = "until";
  public static final String TAG_NAME_EVENTS = "events";

  public static final String ELEMENT = "outsourceevents";

  public EventListingIqRequest() {
    super(ELEMENT);
  }

  public EventListingIqRequest(String element) {
    super(element);
  }

  public EventListingIqRequest(IQ iq) {
    super(iq);
  }

  private Long since;

  private Long until;

  private List<EventIqElement> events;

  public Long getSince() {
    return since;
  }

  public void setSince(Long since) {
    this.since = since;
  }

  public Long getUntil() {
    return until;
  }

  public void setUntil(Long until) {
    this.until = until;
  }

  public List<EventIqElement> getEvents() {
    return events;
  }

  public void setEvents(List<EventIqElement> events) {
    this.events = events;
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }

  @Override
  protected String toXml() {
    StringBuilder sb = new StringBuilder();
    sb.append(tag(TAG_NAME_SINCE, since));
    sb.append(tag(TAG_NAME_UNTIL, until));
    sb.append(buildEventsXml());

    return sb.toString();
  }

  private String buildEventsXml() {
    StringBuilder sb = new StringBuilder();
    sb.append(openTag(TAG_NAME_EVENTS));
    for (EventIqElement event : (events == null ? Collections.<EventIqElement>emptyList() : events)) {
      sb.append(event.toString());
    }
    sb.append(closeTag(TAG_NAME_EVENTS));
    return sb.toString();
  }


}

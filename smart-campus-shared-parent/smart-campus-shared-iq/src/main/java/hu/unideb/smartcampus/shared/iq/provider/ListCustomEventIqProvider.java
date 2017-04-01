package hu.unideb.smartcampus.shared.iq.provider;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.CUSTOM_EVENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_DESCRIPTION;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_END;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_ID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_NAME;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_PLACE;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_REPEAT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_START;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.REMINDER;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.STUDENT;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListCustomEventIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

/**
 * Custom event provider.
 */
@SuppressWarnings({"PMD"})
public class ListCustomEventIqProvider
    extends BaseSmartCampusIqProvider<ListCustomEventIqRequest> {


  private String student;
  private List<CustomEventIqElement> customEvents;
  private CustomEventIqElement customEvent;
  private boolean done;

  @Override
  public ListCustomEventIqRequest parse(XmlPullParser parser, int initialDepth) throws Exception {
    customEvents = new ArrayList<>();
    customEvent = new CustomEventIqElement();
    done = false;
    int eventType = parser.getEventType();
    String text = "";
    while (!done) {
      eventType = parser.next();
      String tagname = parser.getName();
      switch (eventType) {
        case XmlPullParser.START_TAG:
          parseStartTag(tagname);
          break;
        case XmlPullParser.TEXT:
          text = parser.getText();
          break;
        case XmlPullParser.END_TAG:
          parseEndTag(text, tagname);
          break;
        default:
          break;
      }
    }
    return new ListCustomEventIqRequest(student, customEvents);
  }

  private void parseStartTag(String tagname) {
    if (tagname.equalsIgnoreCase(CUSTOM_EVENT)) {
      customEvent = new CustomEventIqElement();
    }
  }

  private void parseEndTag(String text, String tagname) {
    if (tagname.equalsIgnoreCase(CUSTOM_EVENT)) {
      customEvents.add(customEvent);
    } else if (tagname.equalsIgnoreCase(STUDENT)) {
      student = text;
    } else if (tagname.equalsIgnoreCase(EVENT_ID)) {
      customEvent.setEventId(Long.valueOf(text));
    } else if (tagname.equalsIgnoreCase(EVENT_NAME)) {
      customEvent.setEventName(text);
    } else if (tagname.equalsIgnoreCase(EVENT_DESCRIPTION)) {
      customEvent.setEventDescription(text);
    } else if (tagname.equalsIgnoreCase(EVENT_PLACE)) {
      customEvent.setEventPlace(text);
    } else if (tagname.equalsIgnoreCase(EVENT_START)) {
      customEvent.setEventStart(Long.valueOf(text));
    } else if (tagname.equalsIgnoreCase(EVENT_END)) {
      customEvent.setEventEnd(Long.valueOf(text));
    } else if (tagname.equalsIgnoreCase(EVENT_REPEAT)) {
      customEvent.setEventRepeat(text);
    } else if (tagname.equalsIgnoreCase(REMINDER)) {
      customEvent.setReminder(text);
    } else if (tagname.equals(ListCustomEventIqRequest.ELEMENT)) {
      done = true;
    }
  }

  @Override
  public Class<? extends BaseSmartCampusIqRequest> getHandledIq() {
    return ListCustomEventIqRequest.class;
  }

}

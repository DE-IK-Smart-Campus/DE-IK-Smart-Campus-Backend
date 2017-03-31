package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.CUSTOM_EVENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.CUSTOM_EVENTS;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_DESCRIPTION;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_END;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_NAME;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_PLACE;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_REPEAT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_START;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.REMINDER;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.STUDENT;

import java.util.ArrayList;
import java.util.List;

import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Custom event IQ request.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CustomEventListIqRequest extends BaseSmartCampusIqRequest {

  /**
   * Element.
   */
  public static final String ELEMENT = "customEventList";

  /**
   * Student's username.
   */
  private String student;

  /**
   * Student's custom events.
   */
  private List<CustomEventIqElement> customEvents;

  /**
   * Default constructor.
   */
  public CustomEventListIqRequest() {
    super(ELEMENT);
    customEvents = new ArrayList<>();
  }

  /**
   * Constructs.
   */
  @Builder
  public CustomEventListIqRequest(String student, List<CustomEventIqElement> customEvents) {
    super(ELEMENT);
    this.student = student;
    this.customEvents = customEvents;
  }

  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    builder.append(tag(STUDENT, student));
    buildEvents(builder);
  }

  private void buildEvents(StringBuilder builder) {
    if (customEvents != null && !customEvents.isEmpty()) {
      builder.append(openTag(CUSTOM_EVENTS));
      for (CustomEventIqElement customIqElement : customEvents) {
        buildEvent(builder, customIqElement);
      }
      builder.append(closeTag(CUSTOM_EVENTS));
    }
  }

  private void buildEvent(StringBuilder builder, CustomEventIqElement customIqElement) {
    builder.append(openTag(CUSTOM_EVENT));
    builder.append(tag(EVENT_NAME, customIqElement.getEventName()));
    builder.append(tag(EVENT_DESCRIPTION, customIqElement.getEventDescription()));
    builder.append(tag(EVENT_PLACE, customIqElement.getEventPlace()));
    builder.append(tag(EVENT_START, customIqElement.getEventStart()));
    builder.append(tag(EVENT_END, customIqElement.getEventEnd()));
    builder.append(tag(EVENT_REPEAT, customIqElement.getEventRepeat()));
    builder.append(tag(REMINDER, customIqElement.getReminder()));
    builder.append(closeTag(CUSTOM_EVENT));
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }
}

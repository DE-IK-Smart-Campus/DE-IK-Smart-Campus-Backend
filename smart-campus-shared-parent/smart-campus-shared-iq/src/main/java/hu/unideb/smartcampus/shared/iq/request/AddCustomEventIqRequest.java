package hu.unideb.smartcampus.shared.iq.request;

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

import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Add custom event IQ request.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AddCustomEventIqRequest extends BaseSmartCampusIqRequest {

  /**
   * Element.
   */
  public static final String ELEMENT = "addCustomEvent";

  /**
   * Student's username.
   */
  private String student;

  /**
   * Custom IQ element.
   */
  private CustomEventIqElement customEvent;

  /**
   * Default constructor.
   */
  public AddCustomEventIqRequest() {
    super(ELEMENT);
  }

  /**
   * Constructs.
   */
  @Builder
  public AddCustomEventIqRequest(String student, CustomEventIqElement customEvent) {
    super(ELEMENT);
    this.student = student;
    this.customEvent = customEvent;
  }

  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    builder.append(tag(STUDENT, student));
    buildEvent(builder);
  }

  private void buildEvent(StringBuilder builder) {
    builder.append(openTag(CUSTOM_EVENT));
    builder.append(tag(EVENT_ID, customEvent.getEventId()));
    builder.append(tag(EVENT_NAME, customEvent.getEventName()));
    builder.append(tag(EVENT_DESCRIPTION, customEvent.getEventDescription()));
    builder.append(tag(EVENT_PLACE, customEvent.getEventPlace()));
    builder.append(tag(EVENT_START, customEvent.getEventStart()));
    builder.append(tag(EVENT_END, customEvent.getEventEnd()));
    builder.append(tag(EVENT_REPEAT, customEvent.getEventRepeat()));
    builder.append(tag(REMINDER, customEvent.getReminder()));
    builder.append(closeTag(CUSTOM_EVENT));
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }
}

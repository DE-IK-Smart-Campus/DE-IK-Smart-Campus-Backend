package hu.unideb.smartcampus.shared.iq.request.element;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.CUSTOM_EVENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_DESCRIPTION;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_END;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_NAME;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_PLACE;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_REPEAT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_START;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.GUID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.REMINDER;
import static hu.unideb.smartcampus.shared.iq.util.IqProviderUtil.closeTag;
import static hu.unideb.smartcampus.shared.iq.util.IqProviderUtil.openTag;
import static hu.unideb.smartcampus.shared.iq.util.IqProviderUtil.tag;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * Custom event IQ element.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CustomEventIqElement {

  /**
   * Event name.
   */
  private String eventName;

  /**
   * Event description.
   */
  private String eventDescription;

  /**
   * Event place.
   */
  private String eventPlace;

  /**
   * Event start.
   */
  private Long eventStart;

  /**
   * Event end.
   */
  private Long eventEnd;

  /**
   * Event repeat.
   */
  private String eventRepeat;

  /**
   * Reminder.
   */
  private String reminder;

  /**
   * Global unique ID.
   */
  private String guid;

  /**
   * Builder.
   */
  @Builder
  public CustomEventIqElement(String eventName, String eventDescription,
      String eventPlace,
      Long eventStart, Long eventEnd, String eventRepeat, String reminder, String guid) {
    this.eventName = eventName;
    this.eventDescription = eventDescription;
    this.eventPlace = eventPlace;
    this.eventStart = eventStart;
    this.eventEnd = eventEnd;
    this.eventRepeat = eventRepeat;
    this.reminder = reminder;
    this.guid = guid;
  }

  public String toXml() {
    StringBuilder builder = new StringBuilder();
    builder.append(openTag(CUSTOM_EVENT));
    builder.append(tag(EVENT_NAME, eventName));
    builder.append(tag(EVENT_DESCRIPTION, eventDescription));
    builder.append(tag(EVENT_PLACE, eventPlace));
    builder.append(tag(EVENT_START, eventStart));
    builder.append(tag(EVENT_END, eventEnd));
    builder.append(tag(EVENT_REPEAT, eventRepeat));
    builder.append(tag(REMINDER, reminder));
    builder.append(tag(GUID, guid));
    builder.append(closeTag(CUSTOM_EVENT));
    return builder.toString();
  }



}

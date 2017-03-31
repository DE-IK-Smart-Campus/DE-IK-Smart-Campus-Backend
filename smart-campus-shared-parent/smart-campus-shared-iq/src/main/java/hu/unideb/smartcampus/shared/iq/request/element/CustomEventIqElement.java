package hu.unideb.smartcampus.shared.iq.request.element;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Custom event IQ element.
 */
@Data
@NoArgsConstructor
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
   * Builder.
   */
  @Builder
  public CustomEventIqElement(String eventName, String eventDescription, String eventPlace,
      Long eventStart, Long eventEnd, String eventRepeat, String reminder) {
    this.eventName = eventName;
    this.eventDescription = eventDescription;
    this.eventPlace = eventPlace;
    this.eventStart = eventStart;
    this.eventEnd = eventEnd;
    this.eventRepeat = eventRepeat;
    this.reminder = reminder;
  }



}

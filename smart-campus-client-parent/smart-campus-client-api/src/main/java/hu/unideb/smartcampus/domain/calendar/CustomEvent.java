package hu.unideb.smartcampus.domain.calendar;

import lombok.Builder;
import lombok.Data;

@Data
public class CustomEvent {
  /**
   * Global unique identifier.
   */
  private final String guid;

  /**
   * Event name.
   */
  private final String eventName;

  /**
   * Event description.
   */
  private final String eventDescription;

  /**
   * Event place.
   */
  private final String eventPlace;

  /**
   * Event start.
   */
  private final Long eventStart;

  /**
   * Event end.
   */
  private final Long eventEnd;

  /**
   * Event repeat.
   */
  private final String eventRepeat;

  /**
   * Reminder.
   */
  private final String reminder;

  /**
   * Constructs custom event entity.
   */
  @Builder
  public CustomEvent(final String guid, final String eventName,
                     final String eventDescription,
                     final String eventPlace,
                     final Long eventStart, final Long eventEnd,
                     final String eventRepeat,
                     final String reminder) {
    this.guid = guid;
    this.eventName = eventName;
    this.eventDescription = eventDescription;
    this.eventPlace = eventPlace;
    this.eventStart = eventStart;
    this.eventEnd = eventEnd;
    this.eventRepeat = eventRepeat;
    this.reminder = reminder;
  }
}

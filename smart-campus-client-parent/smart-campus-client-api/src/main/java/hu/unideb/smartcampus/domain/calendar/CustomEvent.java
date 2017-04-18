package hu.unideb.smartcampus.domain.calendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
  private final LocalDateTime eventStart;

  /**
   * Event end.
   */
  private final LocalDateTime eventEnd;

  /**
   * Event end.
   */
  private final LocalDate eventWhen;

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
                     final LocalDateTime eventStart, final LocalDateTime eventEnd, final LocalDate eventWhen,
                     final String eventRepeat,
                     final String reminder) {
    this.guid = guid;
    this.eventName = eventName;
    this.eventDescription = eventDescription;
    this.eventPlace = eventPlace;
    this.eventStart = eventStart;
    this.eventEnd = eventEnd;
    this.eventWhen = eventWhen;
    this.eventRepeat = eventRepeat;
    this.reminder = reminder;
  }
}

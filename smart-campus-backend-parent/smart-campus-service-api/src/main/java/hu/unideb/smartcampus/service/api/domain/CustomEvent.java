package hu.unideb.smartcampus.service.api.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Custom event domain.
 */
@Data
@ToString(callSuper = true)
public class CustomEvent extends BaseObject<Long> {
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
  public CustomEvent(final Long id, final String guid, final String eventName,
      final String eventDescription,
      final String eventPlace,
      final LocalDateTime eventStart, final LocalDateTime eventEnd,
      final String eventRepeat,
      final String reminder) {
    super(id);
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

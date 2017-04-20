package hu.unideb.smartcampus.persistence.entity;

import static hu.unideb.smartcampus.shared.table.TableName.TABLE_NAME_CUSTOM_EVENT;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import hu.unideb.smartcampus.persistence.listener.CustomEventEntityListener;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Custom event entity.
 */
@Data
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = TABLE_NAME_CUSTOM_EVENT)
@NamedQueries({@NamedQuery(name = "CustomEventEntity.getEventsByUsername",
    query = "SELECT e.customEvents FROM UserEntity e WHERE e.username = ?1 "),
    @NamedQuery(name = "CustomEventEntity.getEventsByUserId",
        query = "SELECT e.customEvents FROM UserEntity e WHERE e.id = ?1 ")})
@EntityListeners(CustomEventEntityListener.class)
public class CustomEventEntity extends BaseEntity<Long> {

  /**
   * Global unique identifier.
   */
  @Column(name = "guid")
  private String guid;

  /**
   * Event name.
   */
  @Column(name = "event_name")
  private String eventName;

  /**
   * Event description.
   */
  @Column(name = "event_description")
  private String eventDescription;

  /**
   * Event place.
   */
  @Column(name = "event_place")
  private String eventPlace;

  /**
   * Event when.
   */
  @Column(name = "event_when")
  private LocalDate eventWhen;

  /**
   * Event start.
   */
  @Column(name = "event_start")
  private LocalDateTime eventStart;

  /**
   * Event end.
   */
  @Column(name = "event_end")
  private LocalDateTime eventEnd;

  /**
   * Event repeat.
   */
  @Column(name = "event_repeat")
  private String eventRepeat;

  /**
   * Reminder.
   */
  @Column(name = "reminder")
  private String reminder;

  /**
   * Constructs custom event entity.
   */
  @Builder
  public CustomEventEntity(final Long id, final String guid,
      final String eventName,
      final String eventDescription,
      final String eventPlace,
      final LocalDateTime eventStart,
      final LocalDateTime eventEnd,
      final String eventRepeat,
      final String reminder,
      final LocalDate eventWhen) {
    super(id);
    this.guid = guid;
    this.eventName = eventName;
    this.eventDescription = eventDescription;
    this.eventPlace = eventPlace;
    this.eventStart = eventStart;
    this.eventEnd = eventEnd;
    this.eventRepeat = eventRepeat;
    this.reminder = reminder;
    this.eventWhen = eventWhen;
  }


}

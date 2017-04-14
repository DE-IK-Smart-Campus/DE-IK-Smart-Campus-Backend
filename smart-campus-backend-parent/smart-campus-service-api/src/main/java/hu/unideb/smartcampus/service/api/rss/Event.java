package hu.unideb.smartcampus.service.api.rss;

import java.time.LocalDateTime;

import hu.unideb.smartcampus.service.api.domain.BaseObject;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
public class Event extends BaseObject<Long> {

  private String description;

  private String name;

  private LocalDateTime startTime;

  private LocalDateTime endTime;

  private String eventId;

  private Location location;

  @Builder
  public Event(Long id, String description, String name, LocalDateTime startTime, LocalDateTime endTime,
      String eventId, Location location) {
    super(id);
    this.description = description;
    this.name = name;
    this.startTime = startTime;
    this.endTime = endTime;
    this.eventId = eventId;
    this.location = location;
  }

}

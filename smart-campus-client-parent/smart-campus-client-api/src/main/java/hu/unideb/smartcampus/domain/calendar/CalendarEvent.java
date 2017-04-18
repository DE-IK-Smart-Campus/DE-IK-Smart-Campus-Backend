package hu.unideb.smartcampus.domain.calendar;

import lombok.Builder;
import lombok.Data;

@Data
public class CalendarEvent {
  private String title;
  private String start;
  private String end;

  @Builder
  public CalendarEvent(String title, String start, String end) {
    this.title = title;
    this.start = start;
    this.end = end;
  }
}

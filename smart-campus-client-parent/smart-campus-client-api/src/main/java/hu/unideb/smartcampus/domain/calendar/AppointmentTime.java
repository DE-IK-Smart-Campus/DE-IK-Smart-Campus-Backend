package hu.unideb.smartcampus.domain.calendar;

import java.sql.Timestamp;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
public class AppointmentTime {

  private Long id;
  private Long from;
  private Long to;
  private Timestamp when;
  private LocalDate whenInLocalDate;
  private boolean present;

  public AppointmentTime() {
  }

  @Builder
  public AppointmentTime(final Long id, final Long from, final Long to, final Timestamp when, final boolean present) {
    this.id = id;
    this.from = from;
    this.to = to;
    this.when = when;
    this.present = present;
  }
}

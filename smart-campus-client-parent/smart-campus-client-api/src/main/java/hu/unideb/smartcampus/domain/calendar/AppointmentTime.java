package hu.unideb.smartcampus.domain.calendar;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

@Data
public class AppointmentTime {

  private Long from;
  private Long to;
  private Timestamp when;

  @Builder
  public AppointmentTime(final Long from, final Long to, final Timestamp when) {
    this.from = from;
    this.to = to;
    this.when = when;
  }
}

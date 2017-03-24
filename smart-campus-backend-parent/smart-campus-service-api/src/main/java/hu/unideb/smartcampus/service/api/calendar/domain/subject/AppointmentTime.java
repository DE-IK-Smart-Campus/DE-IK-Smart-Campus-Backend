package hu.unideb.smartcampus.service.api.calendar.domain.subject;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
 * AppointmentTime.
 */
@Data
@Builder
public class AppointmentTime {

  /**
   * Start date time.
   */
  private final LocalDateTime startDateTime;

  /**
   * End date time.
   */
  private final LocalDateTime endDateTime;
}

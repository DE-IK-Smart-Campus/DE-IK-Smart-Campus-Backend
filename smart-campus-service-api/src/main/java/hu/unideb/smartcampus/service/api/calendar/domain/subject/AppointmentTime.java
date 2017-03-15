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

  private final LocalDateTime startDate;

  private final LocalDateTime endDate;
}

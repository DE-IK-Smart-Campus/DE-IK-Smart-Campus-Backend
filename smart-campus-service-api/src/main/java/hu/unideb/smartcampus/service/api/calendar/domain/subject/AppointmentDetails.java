package hu.unideb.smartcampus.service.api.calendar.domain.subject;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
 * AppointmentDetails.
 */
@Data
@Builder
public class AppointmentDetails {

  private final LocalDateTime startDate;

  private final LocalDateTime endDate;

  private final String roomLocation;
}

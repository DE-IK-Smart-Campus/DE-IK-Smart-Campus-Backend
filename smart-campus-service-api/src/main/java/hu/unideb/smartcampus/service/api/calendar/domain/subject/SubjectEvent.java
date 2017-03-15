package hu.unideb.smartcampus.service.api.calendar.domain.subject;

import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * SubjectEvent.
 */
@Data
@Builder
public class SubjectEvent {

  private final SubjectDetails subjectDetails;

  private final List<AppointmentDetails> appointmentDetailsList;
}

package hu.unideb.smartcampus.service.api.calendar.domain.subject;

import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * Subject.
 */
@Data
@Builder
public class Subject {

  private final SubjectDetails subjectDetails;

  private final List<AppointmentTime> appointmentTimes;
}

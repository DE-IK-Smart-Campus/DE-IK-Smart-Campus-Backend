package hu.unideb.smartcampus.service.api.calendar.domain.subject;

import java.util.List;
import hu.unideb.smartcampus.service.api.domain.BaseObject;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * SubjectEvent.
 */
@Data
@EqualsAndHashCode(exclude = "appointmentTimeList")
public class SubjectEvent extends BaseObject<Long> {

  /**
   * Subject details.
   */
  private final SubjectDetails subjectDetails;

  /**
   * Room location.
   */
  private final String roomLocation;

  /**
   * Appointment time list.
   */
  private final List<AppointmentTime> appointmentTimeList;

  /**
   * Builder.
   */
  @Builder
  public SubjectEvent(final Long id, final SubjectDetails subjectDetails, final String roomLocation,
                      final List<AppointmentTime> appointmentTimeList) {
    super(id);
    this.subjectDetails = subjectDetails;
    this.roomLocation = roomLocation;
    this.appointmentTimeList = appointmentTimeList;
  }
}

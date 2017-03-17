package hu.unideb.smartcampus.service.api;

import java.util.List;
import java.util.Set;

import hu.unideb.smartcampus.service.api.domain.Subject;
import hu.unideb.smartcampus.shared.officehour.OfficeHour;
import hu.unideb.smartcampus.shared.officehour.OfficeHourIntervall;

/**
 * Consulting hour service.
 *
 */
public interface ConsultingHourService {

  /**
   * Get subjects by user id.
   *
   */
  Set<Subject> getSubjectsByUserId(Long id);

  /**
   * Generate office hours for instructor.
   */
  Integer generateOfficeHoursForInstructor(Long instructorId, List<OfficeHour> officeHours,
      OfficeHourIntervall intervall);

}

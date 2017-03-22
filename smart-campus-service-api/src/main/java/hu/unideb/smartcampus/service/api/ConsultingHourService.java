package hu.unideb.smartcampus.service.api;


import java.util.List;

import hu.unideb.smartcampus.shared.officehour.OfficeHour;
import hu.unideb.smartcampus.shared.officehour.OfficeHourIntervall;
/**
 * Consulting hour service.
 *
 */
public interface ConsultingHourService {

  /**
   * Generate office hours for instructor.
   */
  Integer generateOfficeHoursForInstructor(Long instructorId, List<OfficeHour> officeHours,
      OfficeHourIntervall intervall);
}

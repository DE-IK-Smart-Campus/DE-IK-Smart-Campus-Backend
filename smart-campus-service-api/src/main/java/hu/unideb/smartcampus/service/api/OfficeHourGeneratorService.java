package hu.unideb.smartcampus.service.api;

import java.util.List;

import hu.unideb.smartcampus.service.api.domain.ConsultingDate;
import hu.unideb.smartcampus.shared.officehour.OfficeHour;
import hu.unideb.smartcampus.shared.officehour.OfficeHourIntervall;

/**
 * Service which generates office hours.
 *
 */
public interface OfficeHourGeneratorService {

  /**
   * Generate consulting dates from office hours.
   */
  List<ConsultingDate> generateOfficeHoursInIntervall(OfficeHourIntervall intervall,
      List<OfficeHour> officeHours);
}

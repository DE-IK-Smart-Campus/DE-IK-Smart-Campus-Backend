package hu.unideb.smartcampus.service.api;

import hu.unideb.smartcampus.shared.iq.request.SubjectsIqRequest;

/**
 * Consulting hour service to use IQ.
 */
public interface ConsultingHoursService {

  /**
   * Get student subjects.
   */
  SubjectsIqRequest getSubjects();

}

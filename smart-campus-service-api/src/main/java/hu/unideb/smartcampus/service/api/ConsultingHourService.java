package hu.unideb.smartcampus.service.api;

import java.util.Set;

import hu.unideb.smartcampus.service.api.domain.Subject;

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

}

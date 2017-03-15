package hu.unideb.smartcampus.service.api;

import java.util.Set;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;

/**
 * Consulting hour service.
 *
 */
public interface ConsultingHourService {

  /**
   * Get subjects by user id.
   *
   */
  Set<SubjectDetails> getSubjectsByUserId(Long id);

}

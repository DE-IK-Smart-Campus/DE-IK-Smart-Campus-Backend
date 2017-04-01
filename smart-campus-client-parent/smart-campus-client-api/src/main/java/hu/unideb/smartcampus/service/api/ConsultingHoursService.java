package hu.unideb.smartcampus.service.api;

import java.util.List;
import hu.unideb.smartcampus.domain.Instructor;
import hu.unideb.smartcampus.domain.Subject;

/**
 * Consulting hour service to use IQ.
 */
public interface ConsultingHoursService {

  /**
   * Get subjects of current user.
   */
  List<Subject> getSubjects();

  /**
   * Get instructor by instructor ID.
   */
  Instructor getInstructorByInstructorId(Long instructorId);
}

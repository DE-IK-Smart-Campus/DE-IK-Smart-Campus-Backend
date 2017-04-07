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

  /**
   * Sign up for consulting hour by consulting hour ID.
   * @param consultingHourId consulting hour ID of the consulting hour to sign up.
   * @param duration the duration.
   * @param reason the reason.
   */
  void signUpForConsultingDate(Long consultingHourId, Long duration, String reason);
}

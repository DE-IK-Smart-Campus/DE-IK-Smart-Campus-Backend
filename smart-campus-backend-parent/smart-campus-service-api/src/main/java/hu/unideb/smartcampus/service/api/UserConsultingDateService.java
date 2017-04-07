package hu.unideb.smartcampus.service.api;

import java.util.List;

import hu.unideb.smartcampus.shared.iq.request.element.StudentIqElement;

/**
 * User consulting date service.
 */
public interface UserConsultingDateService {

  /**
   * List signed users within one week by instructor id.
   */
  List<StudentIqElement> findSignedStudentByInstructorIdWithinOneWeek(Long instructorId);

  /**
   * List all signed users by instructor id.
   */
  List<StudentIqElement> listSignedStudentByInstructorId(Long instructorId);
}

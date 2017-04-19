package hu.unideb.smartcampus.service.api;

import java.util.List;

import hu.unideb.smartcampus.shared.iq.request.element.InstructorConsultingDateIqElement;

/**
 * User consulting date service.
 */
public interface UserConsultingDateService {

  /**
   * List signed users within one week by instructor id.
   */
  List<InstructorConsultingDateIqElement> findSignedStudentByInstructorIdWithinOneWeek(String neptunIdentifier);

  /**
   * List all signed users by instructor id.
   */
  List<InstructorConsultingDateIqElement> listSignedStudentByInstructorId(String neptunIdentifier);
}

package hu.unideb.smartcampus.shared.requestmessages;

import lombok.Builder;
import lombok.Data;

/**
 * Send this message to retrivie the given user's subjects.
 *
 */
@Data
public class RetrieveInstructorConsultingHours extends BaseRequest {

  /**
   * Instructor id.
   */
  private final Long instructorId;

  /**
   * Constructs a RetrieveInsturctorConsultingHours instance.
   */
  @Builder
  public RetrieveInstructorConsultingHours(final String messageType, final Long instructorId) {
    super(messageType);
    this.instructorId = instructorId;
  }
}

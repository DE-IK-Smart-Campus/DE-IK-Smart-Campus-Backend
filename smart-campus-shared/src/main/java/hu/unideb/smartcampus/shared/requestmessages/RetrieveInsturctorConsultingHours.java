package hu.unideb.smartcampus.shared.requestmessages;

import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Send this message to retrivie the given user's subjects.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RetrieveInsturctorConsultingHours extends BaseRequest implements BaseRequestType {

  /**
   * Instructor id.
   */
  private Long instructorId;

  /**
   * Constructor for Jackson.
   */
  public RetrieveInsturctorConsultingHours() {
    super(RequestMessagesConstants.RETRIEVE_CONSULTING_HOURS);
  }

  /**
   * Constructs a RetrieveInsturctorConsultingHours instance.
   */
  public RetrieveInsturctorConsultingHours(final String messageType, final Long instructorId) {
    super(messageType);
    this.instructorId = instructorId;
  }
}

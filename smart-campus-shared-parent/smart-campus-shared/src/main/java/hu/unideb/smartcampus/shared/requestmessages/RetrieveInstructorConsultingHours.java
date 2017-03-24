package hu.unideb.smartcampus.shared.requestmessages;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;

/**
 * Send this message to retrivie the given user's subjects.
 *
 */
@Data
@JsonDeserialize(builder = RetrieveInstructorConsultingHours.RetrieveInstructorConsultingHoursBuilder.class)
@JsonPOJOBuilder(withPrefix = "")
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
  
  /**
   * Builder.
   *
   */
  @JsonPOJOBuilder(withPrefix = "")
  public static final class RetrieveInstructorConsultingHoursBuilder {
  }
}

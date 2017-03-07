package hu.unideb.smartcampus.service.api.domain.response.wrapper;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Instructor consulting hours wrapper.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class InstructorConsultingHoursWrapper extends BaseWrapper {

  /**
   * Consulting hours.
   */
  private List<ConsultingHourWrapper> consultingHours;

  /**
   * Constructs a InstructorConsultingHoursWrapper instance.
   */
  @Builder
  public InstructorConsultingHoursWrapper(final String messageType,
      final List<ConsultingHourWrapper> consultingHours) {
    super(messageType);
    this.consultingHours = consultingHours;
  }



}

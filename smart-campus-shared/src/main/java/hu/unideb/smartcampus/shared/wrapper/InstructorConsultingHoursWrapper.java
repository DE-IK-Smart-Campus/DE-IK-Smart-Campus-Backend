package hu.unideb.smartcampus.shared.wrapper;

import java.util.List;

import hu.unideb.smartcampus.shared.wrapper.inner.ConsultingDateWrapper;
import lombok.Builder;
import lombok.Data;

/**
 * Instructor consulting hours wrapper.
 */
@Data
public class InstructorConsultingHoursWrapper extends BaseWrapper {

  /**
   * Consulting hours.
   */
  private final List<ConsultingDateWrapper> consultingHours;

  /**
   * Constructs a InstructorConsultingHoursWrapper instance.
   */
  @Builder
  public InstructorConsultingHoursWrapper(final String messageType,
      final List<ConsultingDateWrapper> consultingHours) {
    super(messageType);
    this.consultingHours = consultingHours;
  }



}

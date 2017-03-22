package hu.unideb.smartcampus.service.api.domain.response.wrapper;

import lombok.Builder;
import lombok.Data;

/**
 * Office hour response wrapper.
 *
 */
@Data
public class OfficeHourResponseWrapper extends BaseWrapper {

  /**
   * Status.
   */
  private final String status;

  /**
   * Number of generated hours.
   */
  private final Integer numberOfGeneratedHours;

  /**
   * Constructs an OfficeHourResponseWrapper instance.
   */
  @Builder
  public OfficeHourResponseWrapper(final String messageType, final String status,
      final Integer numberOfGeneratedHours) {
    super(messageType);
    this.status = status;
    this.numberOfGeneratedHours = numberOfGeneratedHours;
  }



}

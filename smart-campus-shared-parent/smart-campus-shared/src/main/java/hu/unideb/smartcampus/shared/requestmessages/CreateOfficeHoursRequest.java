package hu.unideb.smartcampus.shared.requestmessages;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import hu.unideb.smartcampus.shared.officehour.OfficeHour;
import hu.unideb.smartcampus.shared.officehour.OfficeHourIntervall;
import lombok.Builder;
import lombok.Data;

/**
 * Request for generating consulting dates by office hours.
 *
 */
@Data
@JsonDeserialize(builder = CreateOfficeHoursRequest.CreateOfficeHoursRequestBuilder.class)
public class CreateOfficeHoursRequest extends BaseRequest {

  /**
   * Instructors id.
   */
  private final Long instructorId;

  /**
   * Office hours intervall.
   */
  private final OfficeHourIntervall intervall;

  /**
   * Office hours in list.
   */
  private final List<OfficeHour> officeHours;

  /**
   * Constructs a CreateOfficeHoursRequest instance.
   */
  @Builder
  public CreateOfficeHoursRequest(final String messageType, final OfficeHourIntervall intervall,
      final List<OfficeHour> officeHours, final Long instructorId) {
    super(messageType);
    this.intervall = intervall;
    this.officeHours = officeHours;
    this.instructorId = instructorId;
  }

  /**
   * Builder.
   *
   */
  @JsonPOJOBuilder(withPrefix = "")
  public static class CreateOfficeHoursRequestBuilder {

  }

}

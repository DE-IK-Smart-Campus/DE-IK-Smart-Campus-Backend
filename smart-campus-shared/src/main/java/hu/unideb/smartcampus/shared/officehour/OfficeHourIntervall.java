package hu.unideb.smartcampus.shared.officehour;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;

/**
 * Class which indicates the office hours intervall within the semester.
 *
 */
@Data
@JsonDeserialize(builder = OfficeHourIntervall.OfficeHourIntervallBuilder.class)
public class OfficeHourIntervall {

  /**
   * Office hour intervall start.
   */
  private final Timestamp fromDate;

  /**
   * Office hour intervall end.
   */
  private final Timestamp toDate;

  /**
   * Constructs an OfficeHourIntervall instance.
   */
  @Builder
  public OfficeHourIntervall(final Timestamp fromDate, final Timestamp toDate) {
    this.fromDate = fromDate;
    this.toDate = toDate;
  }

  /**
   * Builder.
   *
   */
  @JsonPOJOBuilder(withPrefix = "")
  public static final class OfficeHourIntervallBuilder {
  }
}

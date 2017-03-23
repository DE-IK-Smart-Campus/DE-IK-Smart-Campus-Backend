package hu.unideb.smartcampus.shared.officehour;

import java.time.DayOfWeek;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;

/**
 * Office hour domain.
 *
 */
@Data
@JsonDeserialize(builder = OfficeHour.OfficeHourBuilder.class)
public class OfficeHour {

  /**
   * Day of the office hour.
   */
  private final DayOfWeek day;

  /**
   * From in String for example: "10:00".
   */
  private final String from;

  /**
   * To in String for example: "12:00".
   */
  private final String to;

  /**
   * Constructs an OfficeHour instance.
   */
  @Builder
  public OfficeHour(final DayOfWeek day, final String from, final String to) {
    this.day = day;
    this.from = from;
    this.to = to;
  }

  /**
   * Builder.
   *
   */
  @JsonPOJOBuilder(withPrefix = "")
  public static class OfficeHourBuilder {

  }



}

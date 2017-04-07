package hu.unideb.smartcampus.shared.iq.request.element;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.GenerateOfficeHoursIqFields.DAY;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.GenerateOfficeHoursIqFields.FROM;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.GenerateOfficeHoursIqFields.OFFICE_HOUR;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.GenerateOfficeHoursIqFields.TO;
import static hu.unideb.smartcampus.shared.iq.util.IqProviderUtil.closeTag;
import static hu.unideb.smartcampus.shared.iq.util.IqProviderUtil.openTag;
import static hu.unideb.smartcampus.shared.iq.util.IqProviderUtil.tag;

import java.time.DayOfWeek;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Office hour IQ element.
 */
@Data
@NoArgsConstructor
public class OfficeHourIqElement {

  /**
   * Day of the office hour.
   */
  private DayOfWeek day;

  /**
   * From in String for example: "10:00".
   */
  private String from;

  /**
   * To in String for example: "12:00".
   */
  private String to;

  /**
   * Constructs an OfficeHour instance.
   */
  @Builder
  public OfficeHourIqElement(final DayOfWeek day, final String from, final String to) {
    this.day = day;
    this.from = from;
    this.to = to;
  }


  /**
   * Generate XML element.
   */
  public String toXml() {
    StringBuilder builder = new StringBuilder();
    builder.append(openTag(OFFICE_HOUR));
    builder.append(tag(DAY, day));
    builder.append(tag(FROM, from));
    builder.append(tag(TO, to));
    builder.append(closeTag(OFFICE_HOUR));
    return builder.toString();
  }
}

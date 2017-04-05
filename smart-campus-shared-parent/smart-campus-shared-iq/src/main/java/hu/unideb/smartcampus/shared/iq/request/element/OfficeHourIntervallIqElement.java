package hu.unideb.smartcampus.shared.iq.request.element;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.GenerateOfficeHoursIqFields.FROM_DATE;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.GenerateOfficeHoursIqFields.INTERVALL;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.GenerateOfficeHoursIqFields.TO_DATE;
import static hu.unideb.smartcampus.shared.iq.util.IqProviderUtil.closeTag;
import static hu.unideb.smartcampus.shared.iq.util.IqProviderUtil.openTag;
import static hu.unideb.smartcampus.shared.iq.util.IqProviderUtil.tag;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Office hour intervall IQ element.
 */
@Data
@NoArgsConstructor
public class OfficeHourIntervallIqElement {

  /**
   * Office hour intervall start.
   */
  private Long fromDate;

  /**
   * Office hour intervall end.
   */
  private Long toDate;

  /**
   * Constructs an OfficeHourIntervall instance.
   */
  @Builder
  public OfficeHourIntervallIqElement(final Long fromDate, final Long toDate) {
    this.fromDate = fromDate;
    this.toDate = toDate;
  }
  
  /**
   * Generate XML element.
   */
  public String toXml() {
    StringBuilder builder = new StringBuilder();
    builder.append(openTag(INTERVALL));
    builder.append(tag(FROM_DATE, fromDate));
    builder.append(tag(TO_DATE, toDate));
    builder.append(closeTag(INTERVALL));
    return builder.toString();
  }

}

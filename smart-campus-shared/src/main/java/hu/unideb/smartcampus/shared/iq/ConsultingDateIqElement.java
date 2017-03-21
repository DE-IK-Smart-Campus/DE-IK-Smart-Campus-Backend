package hu.unideb.smartcampus.shared.iq;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Consulting hour wrapper.
 */
@Data
@NoArgsConstructor
@XmlRootElement(name = "consultingDate")
@XmlAccessorType(XmlAccessType.NONE)
public class ConsultingDateIqElement {

  /**
   * Consulting date id.
   */
  @XmlElement(name = "consultingDateId")
  private Long consultingDateId;

  /**
   * From to date.
   */
  @XmlElement(name = "fromToDates")
  private FromToDateIqElement fromToDates;

  /**
   * Sum of reserved studenst.
   */
  @XmlElement(name = "reservedSum")
  private Integer reservedSum;

  /**
   * Constructs a ConsultingHourWrapper.
   */
  @Builder
  public ConsultingDateIqElement(final Long consultingHourId, final FromToDateIqElement fromToDates,
      final Integer reservedSum) {
    this.consultingDateId = consultingHourId;
    this.fromToDates = fromToDates;
    this.reservedSum = reservedSum;
  }



}

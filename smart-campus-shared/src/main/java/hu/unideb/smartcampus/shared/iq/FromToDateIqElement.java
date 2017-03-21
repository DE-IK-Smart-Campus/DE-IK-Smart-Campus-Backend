package hu.unideb.smartcampus.shared.iq;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Consulting hour from - to interval in Long.
 *
 */
@Data
@NoArgsConstructor
@XmlRootElement(name = "fromToDate")
@XmlAccessorType(XmlAccessType.NONE)
public class FromToDateIqElement implements Serializable {

  /**
   * UID.
   */
  private static final long serialVersionUID = 7366277020071387665L;

  /**
   * From date in long.
   */
  @XmlElement(name = "from")
  private Long from;

  /**
   * To date in long.
   */
  @XmlElement(name = "to")
  private Long to;

  /**
   * Constructs a FromToDate instance.
   */
  @Builder
  public FromToDateIqElement(Long from, Long to) {
    this.from = from;
    this.to = to;
  }



}

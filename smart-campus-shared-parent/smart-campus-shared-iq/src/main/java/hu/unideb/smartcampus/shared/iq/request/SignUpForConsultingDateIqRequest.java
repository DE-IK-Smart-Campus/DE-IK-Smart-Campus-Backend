package hu.unideb.smartcampus.shared.iq.request;

import javax.xml.bind.annotation.XmlElement;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Instructor consulting hours wrapper.
 */
@Getter
@Setter
@EqualsAndHashCode
public class SignUpForConsultingDateIqRequest extends BaseSmartCampusIq {

  /**
   * Element.
   */
  public static final String ELEMENT = "signUpForConsultingDate";

  /**
   * User's JID.
   */
  @XmlElement(name = "userId")
  private String userId;

  /**
   * Consulting hour id.
   */
  @XmlElement(name = "consultingHourId")
  private Long consultingHourId;

  /**
   * Reason of consulting.
   */
  @XmlElement(name = "reason")
  private String reason;

  /**
   * Duration of consulting.
   */
  @XmlElement(name = "duration")
  private String duration;

  /**
   * Builder.
   */
  @Builder
  public SignUpForConsultingDateIqRequest(String userId, Long consultingHourId, String reason,
      String duration) {
    super(ELEMENT);
    this.userId = userId;
    this.consultingHourId = consultingHourId;
    this.reason = reason;
    this.duration = duration;
  }


  /**
   * Constructs an InstructorConsultingDatesRequestIq instance.
   */
  @Builder
  public SignUpForConsultingDateIqRequest() {
    super(ELEMENT);
  }

  @Override
  protected String getElement() {
    return ELEMENT;
  }

  @Override
  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }


  private void buildIq(StringBuilder builder) {
    builder.append("<signUpForConsultingDate xmlns=\"http://inf.unideb.hu/smartcampus/\">");
    builder.append("<userId>").append(userId).append("</userId>");
    builder.append("<consultingHourId>").append(consultingHourId).append("</consultingHourId>");
    builder.append("<reason>").append(reason).append("</reason>");
    builder.append("<duration>").append(duration).append("</duration>");
//    builder.append("</signUpForConsultingDate>");
  }

}

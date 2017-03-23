package hu.unideb.smartcampus.shared.iq.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
@XmlRootElement(name = SignUpForConsultingDateIqRequest.ELEMENT,
    namespace = BaseSmartCampusIq.BASE_NAMESPACE)
@XmlAccessorType(XmlAccessType.NONE)
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
  protected BaseSmartCampusIq getInstance() {
    return this;
  }

  @Override
  protected String getElement() {
    return ELEMENT;
  }

  @Override
  protected Class getIqClass() {
    return this.getClass();
  }
}

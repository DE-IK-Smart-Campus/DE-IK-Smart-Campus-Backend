package hu.unideb.smartcampus.shared.iq.request;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import hu.unideb.smartcampus.shared.iq.request.element.ConsultingDateIqElement;
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
@XmlRootElement(name = InstructorConsultingDatesIqRequest.ELEMENT,
    namespace = BaseSmartCampusIq.BASE_NAMESPACE)
@XmlAccessorType(XmlAccessType.NONE)
public class InstructorConsultingDatesIqRequest extends BaseSmartCampusIq {

  /**
   * Element.
   */
  public static final String ELEMENT = "instructorConsultingDates";

  /**
   * Instructor id.
   */
  @XmlElement(name = "instructorId")
  private String instructorId;

  /**
   * Consulting hours.
   */
  @XmlElementWrapper
  @XmlElement(name = "consultingDate")
  private List<ConsultingDateIqElement> consultingDates;

  /**
   * Constructs an InstructorConsultingDatesRequestIq instance.
   */
  @Builder
  public InstructorConsultingDatesIqRequest(List<ConsultingDateIqElement> consultingHours) {
    super(ELEMENT);
    this.consultingDates = consultingHours;
  }

  /**
   * Default constructor.
   */
  public InstructorConsultingDatesIqRequest() {
    super(ELEMENT);
    consultingDates = new ArrayList<>();
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

package hu.unideb.smartcampus.shared.iq;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.jivesoftware.smack.packet.ExtensionElement;

import lombok.Builder;
import lombok.Data;

/**
 * Instructor consulting hours wrapper.
 */
@Data
@XmlRootElement(name = InstructorConsultingDatesIqRequest.ELEMENT,
    namespace = AbstractSmartCampusIq.BASE_NAMESPACE)
@XmlAccessorType(XmlAccessType.NONE)
public class InstructorConsultingDatesIqRequest extends AbstractSmartCampusIq {

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
  protected ExtensionElement getExtension() {
    return new InstructorConsultingDatesExtension();
  }

  @Override
  protected Object getInstance() {
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

  /**
   * Class extension.
   */
  private class InstructorConsultingDatesExtension implements ExtensionElement {

    @Override
    public String getElementName() {
      return InstructorConsultingDatesIqRequest.ELEMENT;
    }

    @Override
    public CharSequence toXML() {
      return "";
    }

    @Override
    public String getNamespace() {
      return AbstractSmartCampusIq.BASE_NAMESPACE;
    }

  }

}

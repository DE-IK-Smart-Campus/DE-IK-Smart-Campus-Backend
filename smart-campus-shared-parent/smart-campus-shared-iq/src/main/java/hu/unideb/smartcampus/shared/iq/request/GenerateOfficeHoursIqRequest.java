package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.GenerateOfficeHoursIqFields.CREATED_HOURS;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.GenerateOfficeHoursIqFields.INSTRUCTOR_ID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.GenerateOfficeHoursIqFields.OFFICE_HOURS;

import java.util.List;

import hu.unideb.smartcampus.shared.iq.request.element.OfficeHourIntervallIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.OfficeHourIqElement;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Generate office hours IQ request.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class GenerateOfficeHoursIqRequest extends BaseSmartCampusIqRequest {

  /**
   * Element.
   */
  public static final String ELEMENT = "generateOfficeHour";


  /**
   * Instructors id.
   */
  private Long instructorId;

  /**
   * Office hours intervall.
   */
  private OfficeHourIntervallIqElement intervall;

  /**
   * Office hours in list.
   */
  private List<OfficeHourIqElement> officeHours;

  /**
   * Generated
   */
  private Integer createdHours;


  /**
   * Constructs a generate office hour IQ request.
   */
  @Builder
  public GenerateOfficeHoursIqRequest(Long instructorId,
      OfficeHourIntervallIqElement intervall, List<OfficeHourIqElement> officeHours,
      Integer createdHours) {
    super(ELEMENT);
    this.instructorId = instructorId;
    this.intervall = intervall;
    this.officeHours = officeHours;
    this.createdHours = createdHours;
  }

  /**
   * Default constructor.
   */
  public GenerateOfficeHoursIqRequest() {
    super(ELEMENT);
  }

  @Override
  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    builder.append(tag(INSTRUCTOR_ID, instructorId));
    builder.append(tag(CREATED_HOURS, createdHours));
    builder.append(intervall.toXml());
    buildOfficeHours(builder);
    return builder.toString();
  }

  private void buildOfficeHours(StringBuilder builder) {
    if (officeHours != null) {
      builder.append(openTag(OFFICE_HOURS));
      for (OfficeHourIqElement officeHourIqElement : officeHours) {
        builder.append(officeHourIqElement.toXml());
      }
      builder.append(closeTag(OFFICE_HOURS));
    }
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }

}

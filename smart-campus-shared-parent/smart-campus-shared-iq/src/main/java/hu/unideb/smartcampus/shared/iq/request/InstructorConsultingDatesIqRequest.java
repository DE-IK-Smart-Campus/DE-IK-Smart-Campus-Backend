package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.InstructorConsultingDatesIqRequestFields.CONSULTING_DATE;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.InstructorConsultingDatesIqRequestFields.CONSULTING_DATES;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.InstructorConsultingDatesIqRequestFields.CONSULTING_DATE_ID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.InstructorConsultingDatesIqRequestFields.FROM;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.InstructorConsultingDatesIqRequestFields.FROM_TO_DATE;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.InstructorConsultingDatesIqRequestFields.INSTRUCTORID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.InstructorConsultingDatesIqRequestFields.INSTRUCTORNAME;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.InstructorConsultingDatesIqRequestFields.RESERVED_SUM;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.InstructorConsultingDatesIqRequestFields.TO;

import java.util.ArrayList;
import java.util.List;

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
public class InstructorConsultingDatesIqRequest extends BaseSmartCampusIqRequest {

  /**
   * Element.
   */
  public static final String ELEMENT = "instructorConsultingDates";

  /**
   * Instructor id.
   */
  private String instructorId;
  
  /**
   * Instructor name.
   */
  private String instructorName;

  /**
   * Consulting hours.
   */
  private List<ConsultingDateIqElement> consultingDates;

  /**
   * Constructs an InstructorConsultingDatesRequestIq instance.
   */
  @Builder
  public InstructorConsultingDatesIqRequest(String instructorId,
      List<ConsultingDateIqElement> consultingHours,String instructorName) {
    super(ELEMENT);
    this.instructorId = instructorId;
    this.consultingDates = consultingHours;
    this.instructorName = instructorName;
  }

  /**
   * Default constructor.
   */
  public InstructorConsultingDatesIqRequest() {
    super(ELEMENT);
    consultingDates = new ArrayList<>();
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }

  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    builder.append(tag(INSTRUCTORID, instructorId));
    builder.append(tag(INSTRUCTORNAME, instructorName));
    builder.append(openTag(CONSULTING_DATES));
    buildConsultingDate(builder);
    builder.append(closeTag(CONSULTING_DATES));
  }

  private void buildConsultingDate(StringBuilder builder) {
    for (ConsultingDateIqElement consultingDateIqElement : consultingDates) {
      builder.append(openTag(CONSULTING_DATE));
      builder.append(tag(CONSULTING_DATE_ID, consultingDateIqElement.getConsultingDateId()));
      builder.append(openTag(FROM_TO_DATE));
      builder.append(tag(FROM, consultingDateIqElement.getFromToDates().getFrom()));
      builder.append(tag(TO, consultingDateIqElement.getFromToDates().getTo()));
      builder.append(closeTag(FROM_TO_DATE));
      builder.append(tag(RESERVED_SUM, consultingDateIqElement.getReservedSum()));
      builder.append(closeTag(CONSULTING_DATE));
    }
  }
}

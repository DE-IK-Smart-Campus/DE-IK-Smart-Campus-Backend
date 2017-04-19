package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.DATE;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.DATES;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.INSTRUCTORID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.ONE_WEEK;

import java.util.ArrayList;
import java.util.List;

import hu.unideb.smartcampus.shared.iq.request.element.InstructorConsultingDateIqElement;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * List instructor consulting dates.
 */
@Getter
@Setter
public class ListInstructorConsultingDatesIqRequest extends BaseSmartCampusIqRequest {

  /**
   * Element.
   */
  public static final String ELEMENT = "listInstructorConsultingDates";

  /**
   * Instructor id.
   */
  private String instructorId;

  /**
   * Student's subjects.
   */
  private List<InstructorConsultingDateIqElement> dates;

  /**
   * Set true if you want to list only the upcoming 7 days consulting dates.
   */
  private boolean oneWeek;

  /**
   * Default constructor.
   */
  public ListInstructorConsultingDatesIqRequest() {
    super(ELEMENT);
    dates = new ArrayList<>();
  }

  /**
   * Constructs.
   */
  @Builder
  public ListInstructorConsultingDatesIqRequest(String instructorId,
      List<InstructorConsultingDateIqElement> dates, boolean oneWeek) {
    super(ELEMENT);
    this.oneWeek = oneWeek;
    this.dates = dates;
    this.instructorId = instructorId;
  }

  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    builder.append(tag(INSTRUCTORID, instructorId));
    builder.append(tag(ONE_WEEK, oneWeek));
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    buildDates(builder);
  }

  private void buildDates(StringBuilder builder) {
    if (dates != null || !dates.isEmpty()) {
      builder.append(openTag(DATES));
      for (InstructorConsultingDateIqElement date : dates) {
        builder.append(openTag(DATE));
        builder.append(date.toXml());
        builder.append(closeTag(DATE));
      }
      builder.append(closeTag(DATES));
    }
  }
  @Override
  public String getElement() {
    return ELEMENT;
  }
}

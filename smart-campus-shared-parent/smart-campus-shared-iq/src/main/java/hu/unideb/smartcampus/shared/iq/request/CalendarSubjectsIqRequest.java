package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.DESCRIPTION;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.FROM;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.STUDENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.SUBJECT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.SUBJECT_EVENTS;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.SUBJECT_NAME;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.TO;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.WHEN;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CalendarSubjectIqRequestFields.WHERE;

import java.util.ArrayList;
import java.util.List;

import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Calendar subject IQ request.
 */
@Getter
@Setter
public class CalendarSubjectsIqRequest extends BaseSmartCampusIqRequest {

  /**
   * Element.
   */
  public static final String ELEMENT = "calendarSubjects";

  /**
   * Student's username.
   */
  private String student;

  /**
   * Student's subjects.
   */
  private List<CalendarSubjectIqElement> subjectEvents;

  /**
   * Default constructor.
   */
  public CalendarSubjectsIqRequest() {
    super(ELEMENT);
    subjectEvents = new ArrayList<>();
  }

  /**
   * Constructs.
   */
  @Builder
  public CalendarSubjectsIqRequest(String student, List<CalendarSubjectIqElement> subjectEvents) {
    super(ELEMENT);
    this.student = student;
    this.subjectEvents = subjectEvents;
  }

  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    builder.append(tag(STUDENT, student));
    buildSubjects(builder);
  }

  private void buildSubjects(StringBuilder builder) {
    if (subjectEvents != null || !subjectEvents.isEmpty()) {
      builder.append(openTag(SUBJECT_EVENTS));
      for (CalendarSubjectIqElement calendarIqElement : subjectEvents) {
        builder.append(openTag(SUBJECT));
        builder.append(tag(SUBJECT_NAME, calendarIqElement.getSubjectName()));
        builder.append(tag(WHEN, calendarIqElement.getWhen()));
        builder.append(tag(WHERE, calendarIqElement.getWhere()));
        builder.append(tag(DESCRIPTION, calendarIqElement.getDescription()));
        builder.append(tag(FROM, calendarIqElement.getFrom()));
        builder.append(tag(TO, calendarIqElement.getTo()));
        builder.append(closeTag(SUBJECT));
      }
      builder.append(closeTag(SUBJECT_EVENTS));
    }
  }

  @Override
  public IQChildElementXmlStringBuilder getIQChildElementBuilder(
      IQChildElementXmlStringBuilder xml) {
    xml.append(toXml());
    return xml;
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }
}

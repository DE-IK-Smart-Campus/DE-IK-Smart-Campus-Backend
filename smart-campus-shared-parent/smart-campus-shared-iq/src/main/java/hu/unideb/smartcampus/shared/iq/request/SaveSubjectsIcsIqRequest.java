package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.SaveSubjectsIcsIqFields.ICS;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SaveSubjectsIcsIqFields.STATUS_MESSAGE;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SaveSubjectsIcsIqFields.STUDENT;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Subject saving by ICS IQ request.
 */
@Getter
@Setter
public class SaveSubjectsIcsIqRequest extends BaseSmartCampusIqRequest {

  /**
   * Element.
   */
  public static final String ELEMENT = "saveSubjectsWithICS";

  /**
   * Student's username.
   */
  private String student;

  /**
   * Student's subjects.
   */
  private String ics;

  /**
   * Status message which is the result of the parsing method.
   */
  private String statusMessage;

  /**
   * Default constructor.
   */
  public SaveSubjectsIcsIqRequest() {
    super(ELEMENT);
  }

  /**
   * Constructs.
   */
  @Builder
  public SaveSubjectsIcsIqRequest(String student, String ics, String statusMessage) {
    super(ELEMENT);
    this.student = student;
    this.ics = ics;
    this.statusMessage = statusMessage;
  }

  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    builder.append(tag(STUDENT, student));
    builder.append(tag(ICS, ics));
    builder.append(tag(STATUS_MESSAGE, statusMessage));
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }
}

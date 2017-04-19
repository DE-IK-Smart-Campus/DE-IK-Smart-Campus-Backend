package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.ChangeAttendanceIqFields.APPOINTMENT_ID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ChangeAttendanceIqFields.PRESENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ChangeAttendanceIqFields.STUDENT;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Change attendance IQ request.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class ChangeAttendanceIqRequest extends BaseSmartCampusIqRequest {

  /**
   * Element.
   */
  public static final String ELEMENT = "changeAttendance";

  /**
   * Student's username.
   */
  private String student;

  /**
   * Student's chat list.
   */
  private long appointmentId;

  /**
   * Presented or not.
   */
  private boolean present;
  
  /**
   * Default constructor.
   */
  public ChangeAttendanceIqRequest() {
    super(ELEMENT);
  }

  /**
   * Constructs.
   */
  @Builder
  public ChangeAttendanceIqRequest(String student, Long appointmentId, boolean present) {
    super(ELEMENT);
    this.student = student;
    this.appointmentId = appointmentId;
    this.present = present;
  }

  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    builder.append(tag(STUDENT, student));
    builder.append(tag(APPOINTMENT_ID, appointmentId));
    builder.append(tag(PRESENT, present));
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }
  
  public boolean getPresent() {
    return present;
  }
}

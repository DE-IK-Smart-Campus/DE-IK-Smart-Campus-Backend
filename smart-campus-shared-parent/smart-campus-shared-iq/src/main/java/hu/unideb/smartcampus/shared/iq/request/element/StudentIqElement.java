package hu.unideb.smartcampus.shared.iq.request.element;


import static hu.unideb.smartcampus.shared.iq.constant.Fields.StudentIqElementFields.DURATION;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.StudentIqElementFields.NEPTUN_IDENTIFIER;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.StudentIqElementFields.REASON;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.StudentIqElementFields.STUDENT_NAME;
import static hu.unideb.smartcampus.shared.iq.util.IqProviderUtil.tag;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Student IQ element.
 */
@Data
@NoArgsConstructor
public class StudentIqElement {

  /**
   * Student's name.
   */
  private String studentName;

  /**
   * Neptun identifier.
   */
  private String neptunIdentifier;

  /**
   * Reason of going.
   */
  private String reason;

  /**
   * Duration of the consulting.
   */
  private String duration;

  /**
   * Builder.
   */
  @Builder
  public StudentIqElement(String studentName, String neptunIdentifier, String reason,
      String duration) {
    this.reason = reason;
    this.duration = duration;
    this.studentName = studentName;
    this.neptunIdentifier = neptunIdentifier;
  }

  /**
   * Generates XML.
   */
  public String toXml() {
    StringBuilder builder = new StringBuilder();
    builder.append(tag(STUDENT_NAME, studentName));
    builder.append(tag(NEPTUN_IDENTIFIER, neptunIdentifier));
    builder.append(tag(REASON, reason));
    builder.append(tag(DURATION, duration));
    return builder.toString();
  }

}


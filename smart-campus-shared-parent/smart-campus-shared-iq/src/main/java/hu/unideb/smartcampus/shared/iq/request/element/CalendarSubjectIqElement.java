package hu.unideb.smartcampus.shared.iq.request.element;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Calendar subject IQ element.
 *
 * <p>
 * 
 * <pre>
 * {@code
 * 
 * <when>567</when> <!--in long-->
 * <subjectName>RFT</subjectName>
 * <where>IF 01</where>
 * <desc>John LABORATORY</desc>
 * <from>123</from> <!--in long-->
 * <to>321</to> <!--in long-->
 * 
 *  }
 * </pre>
 * 
 * </p>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class CalendarSubjectIqElement implements Serializable {

  /**
   * UID.
   */
  private static final long serialVersionUID = 6717626218636857093L;

  /**
   * Subject name.
   */
  private String subjectName;

  /**
   * When.
   */
  private Long when;

  /**
   * Where.
   */
  private String where;

  /**
   * Event description.
   */
  private String description;

  /**
   * From in Long from timestamp.
   */
  private Long from;

  /**
   * To in Long from Timestamp.
   */
  private Long to;

  /**
   * Constructs a SubjectWrapper instance.
   */
  @Builder
  public CalendarSubjectIqElement(String subjectName, Long when, String where, String description,
      Long from, Long to) {
    this.subjectName = subjectName;
    this.when = when;
    this.where = where;
    this.description = description;
    this.from = from;
    this.to = to;
  }



}

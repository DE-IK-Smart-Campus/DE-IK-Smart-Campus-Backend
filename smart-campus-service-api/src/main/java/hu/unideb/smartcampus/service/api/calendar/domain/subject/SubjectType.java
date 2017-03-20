package hu.unideb.smartcampus.service.api.calendar.domain.subject;

import lombok.Getter;

/**
 * Type of the subject.
 */
public enum SubjectType {

  LABORATORY('L'),
  LECTURE('E'),
  PRACTICE('G'),
  OTHER('O');

  /**
   * Subject type code.
   */
  @Getter
  private final char subjectTypeCode;

  /**
   * Constructor.
   */
  SubjectType(final char subjectTypeCode) {
    this.subjectTypeCode = subjectTypeCode;
  }
}

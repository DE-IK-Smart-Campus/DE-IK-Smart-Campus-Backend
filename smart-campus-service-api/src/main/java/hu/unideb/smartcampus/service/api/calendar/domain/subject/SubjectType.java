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

  @Getter
  private final char subjectTypeCode;

  SubjectType(final char subjectTypeCode) {
    this.subjectTypeCode = subjectTypeCode;
  }
}

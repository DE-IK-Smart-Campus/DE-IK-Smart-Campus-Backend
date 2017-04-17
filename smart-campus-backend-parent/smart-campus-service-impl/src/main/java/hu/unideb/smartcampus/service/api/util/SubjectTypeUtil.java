package hu.unideb.smartcampus.service.api.util;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectType;

/**
 * Subject type util.
 */
@Component
public class SubjectTypeUtil {

  private static final String SUBJECT_TYPE_PATTERN = "[A-Z]{4}[0-9]{3}[E|L|G](-[A-Z|0-9]{2})?";

  public SubjectType createSubjectType(String courseCode) {
    final char subjectTypeCode = readSubjectType(courseCode);
    return mapSubjectTypeCodeToSubjectType(subjectTypeCode);
  }

  private char readSubjectType(String courseCode) {
    return courseCode.matches(SUBJECT_TYPE_PATTERN) ? courseCode.charAt(7) : 'O';
  }

  protected SubjectType mapSubjectTypeCodeToSubjectType(final char subjectTypeCode) {
    return Lists.newArrayList(SubjectType.values())
        .stream()
        .filter(s -> s.getSubjectTypeCode() == subjectTypeCode)
        .findFirst()
        .orElse(SubjectType.OTHER);
  }

}

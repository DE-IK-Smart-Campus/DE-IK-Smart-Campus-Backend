package hu.unideb.smartcampus.service.api.calendar.parser.impl;

import static hu.unideb.smartcampus.shared.calendar.CaldendarConstants.CLASS_CODE;
import static hu.unideb.smartcampus.shared.calendar.CaldendarConstants.CLASS_NAME;
import static hu.unideb.smartcampus.shared.calendar.CaldendarConstants.SUMMARY_REGEXP;
import static hu.unideb.smartcampus.shared.calendar.CaldendarConstants.TEACHER_NAME;

import com.google.common.collect.Lists;

import net.fortuna.ical4j.model.component.VEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import hu.unideb.smartcampus.service.api.UnparsableCalendarEventSummaryException;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectType;
import hu.unideb.smartcampus.service.api.calendar.parser.CalendarSubjectDetailsParser;

@Component
public class CalendarSubjectDetailsParserImpl implements CalendarSubjectDetailsParser {

  private static final String subjectTypePattern = "[A-Z]{4}[0-9]{3}[E|L|G](-[A-Z|0-9]{2})?";

  @Override
  public SubjectDetails parseSubjectDetails(final VEvent event) throws UnparsableCalendarEventSummaryException {
    Assert.notNull(event);
    Assert.notNull(event.getSummary());
    Assert.notNull(event.getSummary().getValue());

    final String subjectSummary = event.getSummary().getValue();

    final Matcher matcher = Pattern.compile(SUMMARY_REGEXP).matcher(subjectSummary);
    if (!matcher.find()) {
      throw new UnparsableCalendarEventSummaryException();
    }
    return SubjectDetails.builder()
        .subjectName(this.parseSubjectName(matcher))
        .subjectType(this.parseSubjectType(matcher))
        //.instructors(this.parseTeacherNames(matcher))
        .build();
  }

  protected String parseSubjectName(final Matcher matcher) {
    return matcher.group(CLASS_NAME);
  }

  protected List<String> parseTeacherNames(final Matcher matcher) {
    return Lists.newArrayList(matcher.group(TEACHER_NAME).split(";"));
  }

  protected SubjectType parseSubjectType(final Matcher matcher) {
    final String subjectType = matcher.group(CLASS_CODE);
    final char subjectTypeCode = subjectType.matches(subjectTypePattern) ? subjectType.charAt(7) : 'O';
    return this.mapSubjectTypeCodeToSubjectType(subjectTypeCode);
  }

  protected SubjectType mapSubjectTypeCodeToSubjectType(final char subjectTypeCode) {
    return Lists.newArrayList(SubjectType.values())
        .stream()
        .filter(s -> s.getSubjectTypeCode() == subjectTypeCode)
        .findFirst()
        .orElse(SubjectType.OTHER);
  }
}

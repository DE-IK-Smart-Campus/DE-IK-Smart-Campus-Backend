package hu.unideb.smartcampus.service.api.calendar.parser.impl;

import static hu.unideb.smartcampus.shared.calendar.CaldendarConstants.CLASS_CODE;
import static hu.unideb.smartcampus.shared.calendar.CaldendarConstants.CLASS_NAME;
import static hu.unideb.smartcampus.shared.calendar.CaldendarConstants.SUMMARY_REGEXP;
import static hu.unideb.smartcampus.shared.calendar.CaldendarConstants.TEACHER_NAME;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

import hu.unideb.smartcampus.service.api.UnparsableCalendarEventSummaryException;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectType;
import hu.unideb.smartcampus.service.api.calendar.parser.CalendarLocalDateTimeParser;
import hu.unideb.smartcampus.service.api.calendar.parser.CalendarSubjectDetailsParser;
import net.fortuna.ical4j.model.component.VEvent;

@Component
public class CalendarSubjectDetailsParserImpl implements CalendarSubjectDetailsParser {

  private static final String subjectTypePattern = "[A-Z]{4}[0-9]{3}[E|L|G](-[A-Z|0-9]{2})?";

  private final CalendarLocalDateTimeParser calendarLocalDateTimeParser;

  @Autowired
  public CalendarSubjectDetailsParserImpl(final CalendarLocalDateTimeParser calendarLocalDateTimeParser) {
    this.calendarLocalDateTimeParser = calendarLocalDateTimeParser;
  }

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
        .subjectName(createSubjectName(matcher))
        .subjectType(createSubjectType(matcher))
//        .teacherNames(createTeacherNames(matcher))
        .startPeriod(createStartPeriod(event))
        .endPeriod(createEndPeriod(event))
        .build();
  }

  protected String createSubjectName(final Matcher matcher) {
    return matcher.group(CLASS_NAME);
  }

  protected SubjectType createSubjectType(final Matcher matcher) {
    final String subjectType = matcher.group(CLASS_CODE);
    final char subjectTypeCode = subjectType.matches(subjectTypePattern) ? subjectType.charAt(7) : 'O';
    return this.mapSubjectTypeCodeToSubjectType(subjectTypeCode);
  }

  protected List<String> createTeacherNames(final Matcher matcher) {
    return Lists.newArrayList(matcher.group(TEACHER_NAME).split(";|,"));
  }

  protected LocalDate createStartPeriod(final VEvent event) {
    final LocalDateTime result = calendarLocalDateTimeParser.parseStartLocalDateTime(event);
    switch (result.getMonth()) {
      case FEBRUARY: case MARCH: case APRIL: case MAY:
        return result.withMonth(Month.FEBRUARY.getValue()).with(TemporalAdjusters.firstDayOfMonth()).toLocalDate();
      case SEPTEMBER: case OCTOBER: case NOVEMBER: case DECEMBER:
        return result.withMonth(Month.SEPTEMBER.getValue()).with(TemporalAdjusters.firstDayOfMonth()).toLocalDate();
    }
    return result.toLocalDate();
  }

  protected LocalDate createEndPeriod(final VEvent event) {
    final LocalDateTime result = calendarLocalDateTimeParser.parseEndLocalDateTime(event);
    switch (result.getMonth()) {
      case FEBRUARY: case MARCH: case APRIL: case MAY:
        return result.withMonth(Month.MAY.getValue()).with(TemporalAdjusters.lastDayOfMonth()).toLocalDate();
      case SEPTEMBER: case OCTOBER: case NOVEMBER: case DECEMBER:
        return result.withMonth(Month.DECEMBER.getValue()).with(TemporalAdjusters.lastDayOfMonth()).toLocalDate();
    }
    return result.toLocalDate();
  }

  protected SubjectType mapSubjectTypeCodeToSubjectType(final char subjectTypeCode) {
    return Lists.newArrayList(SubjectType.values())
        .stream()
        .filter(s -> s.getSubjectTypeCode() == subjectTypeCode)
        .findFirst()
        .orElse(SubjectType.OTHER);
  }
}

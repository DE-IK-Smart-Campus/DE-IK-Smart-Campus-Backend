package hu.unideb.smartcampus.service.api.impl;

import static hu.unideb.smartcampus.shared.calendar.CaldendarConstants.CLASS_CODE;
import static hu.unideb.smartcampus.shared.calendar.CaldendarConstants.CLASS_NAME;
import static hu.unideb.smartcampus.shared.calendar.CaldendarConstants.SUMMARY_REGEXP;
import static hu.unideb.smartcampus.shared.calendar.CaldendarConstants.TEACHER_NAME;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import hu.unideb.smartcampus.service.api.CaledarEventSummary;
import hu.unideb.smartcampus.service.api.CalendarSummaryParser;
import hu.unideb.smartcampus.service.api.UnparsableCalendarEventSummaryException;

@Service
public class CalendarSummaryParserImpl implements CalendarSummaryParser {

  @Override
  public CaledarEventSummary parseSummary(String summaryAsString)
      throws UnparsableCalendarEventSummaryException {
    Pattern pattern = Pattern.compile(SUMMARY_REGEXP);
    Matcher matcher = pattern.matcher(summaryAsString);
    if (!matcher.find()) {
      throw new UnparsableCalendarEventSummaryException();
    }

    String className = matcher.group(CLASS_NAME);
    String teacherName = matcher.group(TEACHER_NAME);
    String classCode = matcher.group(CLASS_CODE);

    return CaledarEventSummary.builder().classCode(classCode).className(className)
        .teacher(teacherName).build();
  }

}

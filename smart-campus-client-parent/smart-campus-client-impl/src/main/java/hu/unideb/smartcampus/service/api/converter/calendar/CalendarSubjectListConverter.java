package hu.unideb.smartcampus.service.api.converter.calendar;

import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;
import hu.unideb.smartcampus.domain.calendar.CalendarSubject;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;

public class CalendarSubjectListConverter implements Converter<List<CalendarSubjectIqElement>, List<CalendarSubject>> {

  private CalendarSubjectConverter calendarSubjectConverter;

  public CalendarSubjectListConverter() {
    this.calendarSubjectConverter = new CalendarSubjectConverter();
  }

  @Override
  public List<CalendarSubject> convert(List<CalendarSubjectIqElement> calendarSubjectIqElements) {
    return calendarSubjectIqElements.stream().map(
        calendarSubjectIqElement -> calendarSubjectConverter.convert(calendarSubjectIqElement)
    ).collect(Collectors.toList());
  }
}

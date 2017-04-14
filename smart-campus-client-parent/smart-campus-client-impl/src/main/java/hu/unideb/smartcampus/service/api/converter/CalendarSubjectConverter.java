package hu.unideb.smartcampus.service.api.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.List;
import hu.unideb.smartcampus.domain.calendar.AppointmentTime;
import hu.unideb.smartcampus.domain.calendar.CalendarSubject;
import hu.unideb.smartcampus.shared.iq.request.element.AppointmentTimeIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;

public class CalendarSubjectConverter implements Converter<CalendarSubjectIqElement, CalendarSubject> {

  private Converter<List<AppointmentTimeIqElement>, List<AppointmentTime>> appointmentTimeListConverter;

  public CalendarSubjectConverter() {
    this.appointmentTimeListConverter = new AppointmentTimeListConverter();
  }

  @Override
  public CalendarSubject convert(CalendarSubjectIqElement calendarSubjectIqElement) {
    return new CalendarSubject(
        calendarSubjectIqElement.getSubjectName(),
        calendarSubjectIqElement.getWhere(),
        calendarSubjectIqElement.getDescription(),
        this.appointmentTimeListConverter.convert(calendarSubjectIqElement.getAppointmentTimes())
    );
  }
}

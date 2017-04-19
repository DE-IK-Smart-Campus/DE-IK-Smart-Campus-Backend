package hu.unideb.smartcampus.service.api.converter.calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import hu.unideb.smartcampus.domain.calendar.AppointmentTime;
import hu.unideb.smartcampus.domain.calendar.CalendarSubject;
import hu.unideb.smartcampus.shared.iq.request.element.AppointmentTimeIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;

public class CalendarSubjectConverter implements Converter<CalendarSubjectIqElement, CalendarSubject> {

  private static final Logger LOGGER = LoggerFactory.getLogger(CalendarSubjectConverter.class);

  private Converter<List<AppointmentTimeIqElement>, List<AppointmentTime>> appointmentTimeListConverter;

  public CalendarSubjectConverter() {
    this.appointmentTimeListConverter = new AppointmentTimeListConverter();
  }

  @Override
  public CalendarSubject convert(CalendarSubjectIqElement calendarSubjectIqElement) {
    LOGGER.info("ID: {}",calendarSubjectIqElement.getId());
    return new CalendarSubject(
        calendarSubjectIqElement.getId(),
        calendarSubjectIqElement.getSubjectName(),
        calendarSubjectIqElement.getWhere(),
        calendarSubjectIqElement.getDescription(),
        this.appointmentTimeListConverter.convert(calendarSubjectIqElement.getAppointmentTimes())
    );
  }
}

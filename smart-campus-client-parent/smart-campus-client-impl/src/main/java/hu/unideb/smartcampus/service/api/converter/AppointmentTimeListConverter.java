package hu.unideb.smartcampus.service.api.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;
import hu.unideb.smartcampus.domain.calendar.AppointmentTime;
import hu.unideb.smartcampus.shared.iq.request.element.AppointmentTimeIqElement;

public class AppointmentTimeListConverter implements Converter<List<AppointmentTimeIqElement>, List<AppointmentTime>> {

  private Converter<AppointmentTimeIqElement, AppointmentTime> appointmentTimeConverter;

  public AppointmentTimeListConverter() {
    this.appointmentTimeConverter = new AppointmentTimeConverter();
  }

  @Override
  public List<AppointmentTime> convert(List<AppointmentTimeIqElement> appointmentTimeIqElements) {
    return appointmentTimeIqElements.stream().map(
        appointmentTimeIqElement -> appointmentTimeConverter.convert(appointmentTimeIqElement)
    ).collect(Collectors.toList());
  }
}
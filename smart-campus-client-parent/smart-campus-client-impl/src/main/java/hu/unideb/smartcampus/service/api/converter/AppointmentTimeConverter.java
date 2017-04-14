package hu.unideb.smartcampus.service.api.converter;

import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import hu.unideb.smartcampus.domain.calendar.AppointmentTime;
import hu.unideb.smartcampus.shared.iq.request.element.AppointmentTimeIqElement;

public class AppointmentTimeConverter implements Converter<AppointmentTimeIqElement, AppointmentTime> {
  @Override
  public AppointmentTime convert(AppointmentTimeIqElement appointmentTimeIqElement) {
    return new AppointmentTime(
        appointmentTimeIqElement.getFrom(),
        appointmentTimeIqElement.getTo(),
        new Timestamp(appointmentTimeIqElement.getWhen())
    );
  }
}

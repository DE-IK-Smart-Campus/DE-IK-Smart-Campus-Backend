package hu.unideb.smartcampus.service.api.converter.calendar;

import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.time.Instant;
import hu.unideb.smartcampus.domain.calendar.AppointmentTime;
import hu.unideb.smartcampus.shared.iq.request.element.AppointmentTimeIqElement;

public class AppointmentTimeConverter implements Converter<AppointmentTimeIqElement, AppointmentTime> {
  @Override
  public AppointmentTime convert(AppointmentTimeIqElement appointmentTimeIqElement) {
    return new AppointmentTime(
        appointmentTimeIqElement.getId(),
        appointmentTimeIqElement.getFrom(),
        appointmentTimeIqElement.getTo(),
        Timestamp.from(Instant.ofEpochSecond(appointmentTimeIqElement.getWhen())),
        appointmentTimeIqElement.isPresent()
    );
  }
}

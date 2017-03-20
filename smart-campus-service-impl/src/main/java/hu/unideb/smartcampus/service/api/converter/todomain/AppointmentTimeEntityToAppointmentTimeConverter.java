package hu.unideb.smartcampus.service.api.converter.todomain;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.AppointmentTimeEntity;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.AppointmentTime;

@Component
public class AppointmentTimeEntityToAppointmentTimeConverter implements Converter<AppointmentTimeEntity, AppointmentTime> {

  @Override
  public AppointmentTime convert(AppointmentTimeEntity appointmentTimeEntity) {
    return appointmentTimeEntity == null ? null : convertAppointmentTimeEntityToAppointmentTime(appointmentTimeEntity);
  }

  private AppointmentTime convertAppointmentTimeEntityToAppointmentTime(final AppointmentTimeEntity appointmentTimeEntity) {
    return AppointmentTime.builder()
        .startDateTime(appointmentTimeEntity.getStartDateTime())
        .endDateTime(appointmentTimeEntity.getEndDateTime())
        .build();
  }
}

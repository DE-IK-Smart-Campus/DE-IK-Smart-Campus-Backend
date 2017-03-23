package hu.unideb.smartcampus.service.api.converter.toentity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.AppointmentTimeEntity;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.AppointmentTime;

@Component
public class AppointmentTimeToAppointmentTimeEntityConverter implements Converter<AppointmentTime, AppointmentTimeEntity> {

  @Override
  public AppointmentTimeEntity convert(final AppointmentTime appointmentTime) {
    return appointmentTime == null ? null : convertAppointmentTimeToAppointmentTimeEntity(appointmentTime);
  }

  private AppointmentTimeEntity convertAppointmentTimeToAppointmentTimeEntity(final AppointmentTime appointmentTime) {
    return AppointmentTimeEntity.builder()
        .startDateTime(appointmentTime.getStartDateTime())
        .endDateTime(appointmentTime.getEndDateTime())
        .build();
  }
}
